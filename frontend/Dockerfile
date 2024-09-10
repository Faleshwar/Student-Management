FROM node:22-alpine

WORKDIR /student-management-frontend/

COPY public/ /student-management-frontend/public
COPY src/ /student-management-frontend/src
COPY package.json /student-management-frontend/
COPY tailwind.config.js /student-management-frontend/tailwind.config.js

RUN npm install

RUN npm run build

CMD [ "npm", "start" ]
