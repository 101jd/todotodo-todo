CREATE TABLE IF NOT EXISTS todolist (
    id VARCHAR(64) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL
);

--INSERT INTO todolist (name, status, priority) VALUES ('test1', 'COMPLETE', 'HIGH'), ('test2', 'IN_PROGRESS', 'MEDIUM');