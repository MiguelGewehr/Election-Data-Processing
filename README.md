# Election Data Processing

A Java system for processing Brazilian electoral data, implementing both majoritarian and proportional voting systems to analyze federal and state deputy elections.

This project was developed as part of the Object-Oriented Programming (POO) course at UFES (Universidade Federal do Espírito Santo).

## Overview

This system processes electoral data from Brazil's Superior Electoral Court (TSE) to generate comprehensive reports about deputy elections. It handles candidate information, vote counts, and produces detailed statistical analysis following Brazil's electoral system rules.

**Note:** The CSV files included in this repository contain reduced datasets (sample data) for demonstration and testing purposes. The original complete datasets can be obtained from:
- Candidate data: https://cdn.tse.jus.br/estatistica/sead/odsele/consulta_cand/consulta_cand_2022.zip
- Voting data: https://cdn.tse.jus.br/estatistica/sead/odsele/votacao_secao/

**Important:** There may be column structure differences between the original TSE files and the format expected by this system, as the implementation follows the specific requirements provided in the course assignment.

## Features

- Process federal and state deputy election data
- Support for proportional voting system calculations
- Candidate and party statistics generation
- Age and gender distribution analysis
- Comprehensive electoral reports (10 different report types)
- Support for party federations
- Vote redistribution handling (nominal vs. legend votes)

## Requirements

- Java 11 or higher
- Apache Ant for building

## Project Structure

```
├── src/                    # Source code
│   ├── model/             # Data models (Candidate, Party, Federation)
│   └── util/              # Utilities (readers, validators, report generator)
├── input/                 # CSV data files organized by state
│   └── acre/             # Example: Acre state data
├── build.xml             # Ant build configuration
└── deputados.jar         # Generated executable JAR
```

## Building

```bash
# Compile source code
ant compile

# Create executable JAR
ant jar

# Clean generated files
ant clean
```

## Usage

```bash
# Run for federal deputies
ant run-federal

# Run for state deputies  
ant run-estadual

# Or execute JAR directly
java -jar deputados.jar --federal input/acre/candidatos.csv input/acre/votacao.csv 02/10/2022
java -jar deputados.jar --estadual input/acre/candidatos.csv input/acre/votacao.csv 02/10/2022
```

## Data Format

The system expects two CSV files:

### candidatos.csv
Contains candidate information including:
- Candidate number and name
- Party affiliation and federation
- Birth date and gender
- Electoral status
- Vote destination type

### votacao.csv
Contains voting data with:
- Vote counts per electoral section
- Candidate or party voted for
- Electoral position (federal/state deputy)

## Generated Reports

The system produces 10 comprehensive reports:
1. Number of available positions
2. Elected candidates (by vote count)
3. Most voted candidates (limited to available positions)
4. Would-be elected under majoritarian system
5. Candidates benefited by proportional system
6. Party vote totals and elected candidates
7. Top and bottom candidates by party
8. Age distribution of elected candidates
9. Gender distribution of elected candidates
10. Total vote summary (nominal vs. legend votes)

