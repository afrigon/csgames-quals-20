FROM node:12.10.0-stretch AS builder
WORKDIR /app

COPY ./package.json ./
RUN npm install

COPY ./ ./
RUN npm run build


FROM nginx:stable
COPY --from=builder /app/build/ /var/www
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx",  "-g", "daemon off;"]
