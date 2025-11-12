# 🚀 Hadoop Examples

This repository brings together a collection of **modern MapReduce jobs**, ideal for study, teaching, research, and inclusion in professional portfolios.

------------------------------------------------------------------------

## 📁 Project Structure

```text
hadoop-examples/
├── input/
│   ├── grades.txt
│   ├── logs.txt
│   ├── sales.csv
│   ├── tweets.txt
│   └── sensors.csv
├── src/
│   └── main/
│       └── java/
│           ├── average/
│           │   └── AverageGrade.java
│           ├── logs/
│           │   └── EndpointCount.java
│           ├── sales/
│           │   └── TotalSalesByProduct.java
│           ├── twitter/
│           │   └── HashtagCount.java
│           └── sensors/
│               └── SensorAverages.java
├── build.sh
└── README.md
```

------------------------------------------------------------------------

## 📦 Included Jobs

### 1️⃣ Average grade per student — `AverageGrade.java`

**Input:** `grades.txt`  
**Output:** average grade per student.

### 2️⃣ Endpoint access count — `EndpointCount.java`

**Input:** `logs.txt`  
**Output:** total number of requests per route/endpoint.

### 3️⃣ Total sales by product — `TotalSalesByProduct.java`

**Input:** `sales.csv`  
**Output:** total revenue per product.

### 4️⃣ Hashtag count — `HashtagCount.java`

**Input:** `tweets.txt`  
**Output:** count of hashtags in tweets.

### 5️⃣ Sensor averages (IoT) — `SensorAverages.java`

**Input:** `sensors.csv`  
**Output:** average readings per sensor.

------------------------------------------------------------------------

## 🏗 Build

Run the script:

```bash
chmod +x build.sh
./build.sh
```

This will generate:

```
hadoop-examples.jar
```

------------------------------------------------------------------------

## ▶️ Running the Jobs

### 1. Upload input files to HDFS

```bash
hdfs dfs -mkdir -p /data
hdfs dfs -put input/* /data
```

### 2. Run the jobs

You have two options:

#### ✅ Run all jobs at once

```bash
./run-all.sh
```

#### 🎯 Run one job at a time

##### Average grades  
```bash
hadoop jar hadoop-examples.jar average.AverageGrade /data/grades.txt /out/grades
```

##### Endpoint count  
```bash
hadoop jar hadoop-examples.jar logs.EndpointCount /data/logs.txt /out/logs
```

##### Total sales  
```bash
hadoop jar hadoop-examples.jar sales.TotalSalesByProduct /data/sales.csv /out/sales
```

##### Hashtag count  
```bash
hadoop jar hadoop-examples.jar twitter.HashtagCount /data/tweets.txt /out/hashtags
```

##### Sensor averages  
```bash
hadoop jar hadoop-examples.jar sensors.SensorAverages /data/sensors.csv /out/sensors
```

------------------------------------------------------------------------

## 📚 Requirements

- Java 8+
- Hadoop 3.3+
- Properly configured environment (Standalone, Pseudo-Distributed, or Docker)

------------------------------------------------------------------------

## 📜 License

Distributed under the **MIT License** — free for academic and professional use.
