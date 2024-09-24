-- Create the batiCuisine database
CREATE DATABASE batiCuisine;

-- Create clients table
CREATE TABLE clients (
    clientID UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    isProfessional BOOLEAN DEFAULT FALSE
);

-- Create projectStatus ENUM type
CREATE TYPE projectStatus AS ENUM ('IN_PROGRESS', 'FINISHED', 'CANCELED');

-- Create projects table
CREATE TABLE projects (
    projectID UUID PRIMARY KEY,
    projectName VARCHAR(100) NOT NULL,
    profitMargin DOUBLE PRECISION,
    totalCost DOUBLE PRECISION,
    projectStatus projectStatus DEFAULT 'IN_PROGRESS',
    surface DOUBLE PRECISION,
    clientID UUID REFERENCES clients(clientID)
);

-- Create quotes table
CREATE TABLE quotes (
    quoteID UUID PRIMARY KEY,
    estimatedAmount DOUBLE PRECISION,
    issueDate DATE NOT NULL,
    validityDate DATE,
    isAccepted BOOLEAN DEFAULT FALSE,
    projectID UUID REFERENCES projects(projectID) ON DELETE CASCADE
);

-- Create componentType ENUM type
CREATE TYPE componentType AS ENUM ('LABOR', 'MATERIAL');

-- Create components table
CREATE TABLE components (
    componentID UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    componentType componentType NOT NULL,
    VAT_rate DOUBLE PRECISION NOT NULL,
    projectID UUID REFERENCES projects(projectID) ON DELETE CASCADE
);

-- Create materials table, inheriting from components
CREATE TABLE materials (
    transportCost DOUBLE PRECISION,
    qualityCoefficient DOUBLE PRECISION,
    quantity DOUBLE PRECISION,
    unitCost DOUBLE PRECISION
) INHERITS (components);

-- Create labors table, inheriting from components
CREATE TABLE labors (
    hourlyRate DOUBLE PRECISION,
    workingHours DOUBLE PRECISION,
    workerProductivity DOUBLE PRECISION
) INHERITS (components);
