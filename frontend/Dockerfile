FROM nginx:1.23.1-alpine
#ENV NODE_ENV production
COPY build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
