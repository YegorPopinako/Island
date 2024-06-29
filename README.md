# Java Multithreading Island Simulation Project

## Project Overview

This project involves creating a simulation model of an island with variable parameters. The island is represented by a grid of locations (e.g., 100x20 cells). These locations will be populated with plants and animals. Animals in this simulation can:

- Eat plants and/or other animals if suitable food is available in their location
- Move to neighboring locations
- Reproduce if a mate is available in the location
- Die from hunger or being eaten

## Object-Oriented Programming (OOP)

The simulation uses OOP principles to create a diverse range of animals. All animal types derive from an abstract class `Animal`, which contains common behavior for all animals. Specific animal types can override methods in the `Animal` class to account for their unique characteristics in terms of diet, reproduction, and movement.

### Class Hierarchy

- **Predators**: Wolf, Boa, Fox, Bear, Eagle
- **Omnivorous**: Boar, Mouse, Duck
- **Herbivores**: Horse, Deer, Rabbit, Goat, Sheep, Buffalo, Caterpillar
- **Plants**

### Animal Behavior Probability Table

Below is a table showing the probability (%) that an animal will eat a particular food if they are in the same cell.

|       | Wolf | Boa | Fox | Bear | Eagle | Horse | Deer | Rabbit | Mouse | Goat | Sheep | Boar | Buffalo | Duck | Caterpillar | Plants |
|-------|------|-----|-----|------|-------|-------|------|--------|-------|------|-------|------|---------|------|-------------|--------|
| Wolf  | -    | 0   | 0   | 0    | 0     | 10    | 15   | 60     | 80    | 60   | 70    | 15   | 10      | 40   | 0           | 0      |
| Boa   | 0    | -   | 15  | 0    | 0     | 0     | 0    | 20     | 40    | 0    | 0     | 0    | 0       | 10   | 0           | 0      |
| Fox   | 0    | 0   | -   | 0    | 0     | 0     | 0    | 70     | 90    | 0    | 0     | 0    | 0       | 60   | 40          | 0      |
| Bear  | 0    | 80  | 0   | -    | 0     | 40    | 80   | 80     | 90    | 70   | 70    | 50   | 20      | 10   | 0           | 0      |
| Eagle | 0    | 0   | 10  | 0    | -     | 0     | 0    | 90     | 90    | 0    | 0     | 0    | 0       | 80   | 0           | 0      |
| Horse | 0    | 0   | 0   | 0    | 0     | -     | 0    | 0      | 0     | 0    | 0     | 0    | 0       | 0    | 0           | 100    |
| Deer  | 0    | 0   | 0   | 0    | 0     | 0     | -    | 0      | 0     | 0    | 0     | 0    | 0       | 0    | 0           | 100    |
| Rabbit| 0    | 0   | 0   | 0    | 0     | 0     | 0    | -      | 0     | 0    | 0     | 0    | 0       | 0    | 0           | 100    |
| Mouse | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | -     | 0    | 0     | 0    | 0       | 0    | 90          | 100    |
| Goat  | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | -    | 0     | 0    | 0       | 0    | 0           | 100    |
| Sheep | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | -     | 0    | 0       | 0    | 0           | 100    |
| Boar  | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 50    | 0    | 0     | -    | 0       | 0    | 90          | 100    |
| Buffalo|0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0    | -       | 0    | 0           | 100    |
| Duck  | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0    | 0       | -    | 90          | 100    |
| Caterpillar|0| 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0    | 0       | 0    | -           | 100    |

### Animal Characteristics

| Animal      | Weight (kg) | Max Count per Cell | Movement Speed (cells/turn) | Food Requirement (kg) |
|-------------|-------------|--------------------|-----------------------------|------------------------|
| Wolf        | 50          | 30                 | 3                           | 8                      |
| Boa         | 15          | 30                 | 1                           | 3                      |
| Fox         | 8           | 30                 | 2                           | 2                      |
| Bear        | 500         | 5                  | 2                           | 80                     |
| Eagle       | 6           | 20                 | 3                           | 1                      |
| Horse       | 400         | 20                 | 4                           | 60                     |
| Deer        | 300         | 20                 | 4                           | 50                     |
| Rabbit      | 2           | 150                | 2                           | 0.45                   |
| Mouse       | 0.05        | 500                | 1                           | 0.01                   |
| Goat        | 60          | 140                | 3                           | 10                     |
| Sheep       | 70          | 140                | 3                           | 15                     |
| Boar        | 400         | 50                 | 2                           | 50                     |
| Buffalo     | 700         | 10                 | 3                           | 100                    |
| Duck        | 1           | 200                | 4                           | 0.15                   |
| Caterpillar | 0.01        | 1000               | 0                           | 0                      |
| Plants      | 1           | 200                | N/A                         | N/A                    |

## Multithreading

The simulation is designed to run using multiple threads and thread pool. Specifically:

- A regular thread pool is used within the animal and plant life cycle task to manage their behaviors.

### Key Components

1. **Animal Hierarchy (OOP):**
   - Abstract class `Animal` with common behavior.
   - Specific predator, herbivore and omnivour classes extending `Animal`.

2. **Animal Behavior:**
   - Methods for eating, reproducing, and moving.
   - Specific behaviors implemented in animal classes, statistics are stored in json files.

3. **Multithreading:**
   - Regular thread pool for concurrent animal behavior execution.

4. **Statistics:**
   - Console output showing the state of the island at each simulation step.
