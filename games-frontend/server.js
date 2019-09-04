const express = require('express');
const Eureka = require('eureka-js-client').Eureka;
const os = require('os');

const app = express();
const PORT = 3000;
const PATH_PUBLIC = process.env.GAMES_FRONTEND_PATH || 'dist/games-frontend';

const client = new Eureka({
	instance: {
		app: 'games-frontend',
		hostName: os.hostname(),
		ipAddr: '0.0.0.0',
		statusPageUrl: `http://${os.hostname()}:3000`,
		vipAddress: 'games-frontend',
		port: {
			$: PORT,
			'@enabled': 'true',
		},
		dataCenterInfo: {
			'@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
			name: 'MyOwn',
		},
		registerWithEureka: true,
		fetchRegistry: false,
	},
	eureka: {
		host: 'localhost',
		port: 8761,
		servicePath: '/eureka/apps/',
	},
});

client.start(error => {
	if (error) {
		console.error('Eureka client error: ' + error);
		process.exit(1);
	}
	console.log('games-frontend Eureka client started');
})

app.use(express.static(PATH_PUBLIC));
app.get('*', (req, res) => res.sendfile(PATH_PUBLIC + '/index.html'));

const server = app.listen(PORT, () => {
	console.log('games-frontend listening on port ' || PORT);
});

process.on('SIGTERM', shutDown);
process.on('SIGINT', shutDown);

function shutDown() {
	console.log('Shutting down!');
	client.stop(error => {
		if (error) {
			console.error('Eureka client error: ' + error);
		}
		server.close(() => {
			process.exit(0);
		});
	});
}
