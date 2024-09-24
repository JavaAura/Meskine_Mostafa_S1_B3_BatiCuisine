# BatiCuisine

## Project Description
BatiCuisine is a Java application designed for professionals in the construction and renovation industry, particularly for kitchen projects. The application calculates the total cost of renovation by accounting for both the materials used and labor costs, which are billed by the hour.

## General Objective
The primary goal of BatiCuisine is to facilitate the management of kitchen renovation projects by allowing users to:
- Add, update, and remove components (materials and labor).
- Calculate the total project cost based on selected components.
- Generate and manage quotes for renovation projects.
- Provide a detailed breakdown of labor and material expenses.

## Technologies Used
- **Java**: Main programming language for the application.
- **JDBC**: Used to connect and interact with the database.
- **PostgreSQL**: Database management system.
- **Git**: Version control system to track changes.

## Project Structure
- **DAO/**: Contains classes for data access and persistence management.
- **Database/**: Manages database connection and setup.
- **Model/**: Defines the core data models and entities.
- **Repository/**: Manages data retrieval and storage.
- **Service/**: Contains business logic and service methods.
- **Utility/**: Provides utility classes and reusable helper methods.
- **View/**: Handles the user interface and interaction management.

## Class Diagram and Screens
You can view the class diagram and application screens using the following links:
- [Class Diagram](https://github.com/MesVortex/BatiCuisine/blob/main/resources/classDiagram.pdf)

## Installation and Usage

### Prerequisites
- **Java 8** or later.
- **PostgreSQL** installed and running.
- **JDBC** driver for PostgreSQL.

### Database Setup
1. Create a PostgreSQL database named `batiCuisine`.
2. Run the provided SQL [scripts](https://github.com/MesVortex/BatiCuisine/blob/main/resources/BatiCuisine.sql) to create the necessary tables.

### Running the Application
1. Clone this repository:
   ```bash
   git clone https://github.com/MesVortex/BatiCuisine.git
2. Build the project using Maven or your preferred build tool.
1. To run the compiled JAR file:
   ```bash
   java -jar BatiCuisine_structure/executable/BatiCuisine_structure.jar

## Project Management
- [Project Planning in Jira](https://meskinemsoatafa.atlassian.net/jira/software/projects/KIT/boards/9/backlog?epics=visible)

## Presentation
- [Canva Project Presentation](https://www.canva.com/design/DAGRtkHXIC0/nYfyPCtItK_mIoEu6vrS7Q/edit?utm_content=DAGRtkHXIC0&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

## Author
- **Meskine Mostafa**
  - Email: meskinemostafa4@gmail.com
  - GitHub: [github.com/MesVortex](https://github.com/MesVortex)