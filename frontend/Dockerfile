# Use the official Node.js 14 image as the base image
FROM node:latest

# Set the working directory inside the Docker image
WORKDIR /app

# Copy package.json and package-lock.json (if available)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire project directory into the Docker image
COPY . .

# Build the Angular application
RUN npm run build

# Expose the port on which your Angular application will run (e.g., 4200)
EXPOSE 4200

# Start the Angular application
CMD ["npm", "start"]
