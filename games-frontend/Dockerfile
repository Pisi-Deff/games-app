FROM node

ENV GAMES_FRONTEND_PATH '/games-frontend'

WORKDIR /usr/src/app

COPY server/package*.json ./
RUN npm install

COPY server/. .
COPY dist/games-frontend ./games-frontend

EXPOSE 3000/tcp
CMD ["node", "server.js"]
