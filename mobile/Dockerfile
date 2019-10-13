FROM node:10-alpine
ENV NODE_ENV production

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

RUN chown -R node:node /opt/app
USER node

CMD ["npm", "start"]
