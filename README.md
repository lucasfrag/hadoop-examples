# Hadoop MapReduce Examples

Este projeto contém uma coleção de jobs MapReduce modernos, úteis para
estudos, aulas e portfólio profissional.

## 📁 Estrutura do projeto

    hadoop-mapreduce-examples/
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

------------------------------------------------------------------------

## 📦 Jobs incluídos

### 1️⃣ Média de notas por aluno --- `AverageGrade.java`

Entrada: `grades.txt`\
Saída: média das notas por aluno.

### 2️⃣ Contagem de acessos por endpoint --- `EndpointCount.java`

Entrada: `logs.txt`\
Saída: quantidade de acessos por rota/endpoint.

### 3️⃣ Total de vendas por produto --- `TotalSalesByProduct.java`

Entrada: `sales.csv`\
Saída: faturamento total por produto.

### 4️⃣ Contagem de hashtags --- `HashtagCount.java`

Entrada: `tweets.txt`\
Saída: contagem de hashtags.

### 5️⃣ Média por sensor (IoT) --- `SensorAverages.java`

Entrada: `sensors.csv`\
Saída: média das leituras por sensor.

------------------------------------------------------------------------

## 🏗 Compilação

Execute o script:

``` bash
chmod +x build.sh
./build.sh
```

Isso gerará:

    hadoop-mapreduce-examples.jar

------------------------------------------------------------------------

## ▶️ Execução dos jobs

Antes, envie os arquivos para o HDFS:

``` bash
hdfs dfs -mkdir -p /data
hdfs dfs -put input/* /data
```

### Média de notas

``` bash
hadoop jar hadoop-mapreduce-examples.jar average.AverageGrade /data/grades.txt /out/grades
```

### Contagem de endpoints

``` bash
hadoop jar hadoop-mapreduce-examples.jar logs.EndpointCount /data/logs.txt /out/logs
```

### Total de vendas

``` bash
hadoop jar hadoop-mapreduce-examples.jar sales.TotalSalesByProduct /data/sales.csv /out/sales
```

### Contagem de hashtags

``` bash
hadoop jar hadoop-mapreduce-examples.jar twitter.HashtagCount /data/tweets.txt /out/hashtags
```

### Média por sensor

``` bash
hadoop jar hadoop-mapreduce-examples.jar sensors.SensorAverages /data/sensors.csv /out/sensors
```

------------------------------------------------------------------------

## 📚 Requisitos

-   Java 8+
-   Hadoop 3.3+
-   Ambiente configurado (Standalone, Pseudo-Distributed ou Docker)

------------------------------------------------------------------------

## 📜 Licença

MIT License --- livre para uso acadêmico e profissional.
