FROM node:alpine
RUN apk add --no-cache gettext
COPY serve-entrypoint.sh /
COPY ./build /app
COPY inject.template.js /app
ENV PATH /app/node_modules/.bin:$PATH
RUN npm i -g serve
EXPOSE 80
# CMD ["serve", "-s", "build"]
ENTRYPOINT [ "sh", "./serve-entrypoint.sh" ]