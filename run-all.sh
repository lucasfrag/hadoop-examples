#!/bin/bash
set -euo pipefail

JAR=hadoop-examples.jar
HDFS_INPUT=/data

# ensure input exists
hdfs dfs -ls ${HDFS_INPUT} || { echo "No input on HDFS at ${HDFS_INPUT}"; exit 1; }

# Average
hdfs dfs -rm -r /out/avg 2>/dev/null || true
hadoop jar ${JAR} average.AverageGrade ${HDFS_INPUT}/grades.txt /out/avg
echo "== Average result =="
hdfs dfs -cat /out/avg/part-r-00000 || true
echo

# EndpointCount
hdfs dfs -rm -r /out/logs 2>/dev/null || true
hadoop jar ${JAR} logs.EndpointCount ${HDFS_INPUT}/logs.txt /out/logs
echo "== Logs result =="
hdfs dfs -cat /out/logs/part-r-00000 || true
echo

# Hashtags
hdfs dfs -rm -r /out/hashtags 2>/dev/null || true
hadoop jar ${JAR} twitter.HashtagCount ${HDFS_INPUT}/tweets.txt /out/hashtags
echo "== Hashtags result =="
hdfs dfs -cat /out/hashtags/part-r-00000 || true
echo

# Sales
hdfs dfs -rm -r /out/sales 2>/dev/null || true
hadoop jar ${JAR} sales.TotalSalesByProduct ${HDFS_INPUT}/sales.csv /out/sales
echo "== Sales result =="
hdfs dfs -cat /out/sales/part-r-00000 || true
echo

# Sensors
hdfs dfs -rm -r /out/sensors 2>/dev/null || true
hadoop jar ${JAR} sensors.SensorAverages ${HDFS_INPUT}/sensors.csv /out/sensors
echo "== Sensors result =="
hdfs dfs -cat /out/sensors/part-r-00000 || true
echo

echo "All jobs finished."
