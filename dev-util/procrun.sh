#!/usr/bin/env sh

# The DataProcessor executable path:
EXECUTABLE="../build/hive-worker/DataProcessor"

# The host address:
HOST="localhost"

# Port definitions:
CLUSTER_PORT="1234"
DATA_PORT="5678"
KERNEL_PORT="9012"

# The device to use for execution:
DEVICE_ID="\"GeForce 9400M G\""

# Kernel parameters:
NUM_DIMENSIONS="1"
OFFSET="0"
GLOBAL_SIZE="1"
LOCAL_SIZE="1"

eval $EXECUTABLE $HOST $CLUSTER_PORT $HOST $DATA_PORT $HOST $KERNEL_PORT $DEVICE_ID $NUM_DIMENSIONS $OFFSET $GLOBAL_SIZE $LOCAL_SIZE

