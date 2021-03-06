/**
 * A data partitioner for the MD5 calculations unroll node.
 */
 
#define DIGEST_LEN 16
#define LONG_LEN sizeof(long)

__kernel void partitionData(__global unsigned char *input, unsigned int dataSize, unsigned int partsCount, __global unsigned char *output, unsigned int outputSize) {

    int wiId = get_global_id(0);
    int tmpId;
    int offset;
    int wiCount = get_global_size(0);
    
    int itemsPerThread = partsCount / wiCount;
    int batch = itemsPerThread * wiCount;
    
    unsigned char digest[DIGEST_LEN];
    unsigned char fromTmp[LONG_LEN], toTmp[LONG_LEN];
    long from, to;
    long subRangeLen;
    int reminder;
    
    int i, j;
    long tmp;
    
    for (i = 0; i < DIGEST_LEN; i++) {
        digest[i] = input[i];
    }
    for (i = 0; i < LONG_LEN; i++) {
        fromTmp[i] = input[i+DIGEST_LEN];
        toTmp[i] = input[i+DIGEST_LEN+LONG_LEN];
    }
    barrier(CLK_GLOBAL_MEM_FENCE);
    from = *((long *)fromTmp);
    to = *((long *)toTmp);
    
    subRangeLen = (to - from) / partsCount;
    reminder = (to - from) % partsCount;
    
    if (itemsPerThread >= 1) {
        for (i = 0; i < itemsPerThread; i++) {
            tmpId = (wiId * itemsPerThread) + i;
            offset = tmpId * dataSize;
            printf("[%d] offset: %d\n", wiId, offset);
            for (j = 0; j < DIGEST_LEN; j++) {
                output[offset + j] = digest[j];
            }           
            tmp = from + (subRangeLen * tmpId);
            *((long *)fromTmp) = tmp;
            printf("[%d] from: %ld\n", tmpId, tmp);
            tmp = from + (subRangeLen * (tmpId + 1)) + reminder;
            *((long *)toTmp) = tmp;
            printf("[%d] to: %ld\n", tmpId, tmp);
            for (j = 0; j < LONG_LEN; j++) {
                output[j+offset+DIGEST_LEN] = fromTmp[j];
                output[j+offset+DIGEST_LEN+LONG_LEN] = toTmp[j];
            }
        }    
    }
    if (partsCount - batch > 0) {
        printf("LOL\n");
        tmpId = wiId + batch;
        offset = tmpId * dataSize;
        printf("[%d] offset: %d\n", wiId, offset);
        if (tmpId <= partsCount-1) {
            for (j = 0; j < DIGEST_LEN; j++) {
                output[offset + j] = digest[j];
            }
            tmp = from + (subRangeLen * tmpId);
            *((long *)fromTmp) = tmp;
            printf("[%d] from: %ld\n", tmpId, tmp);
            tmp = from + (subRangeLen * (tmpId + 1)) + reminder;
            *((long *)toTmp) = tmp;
            printf("[%d] to: %ld\n", tmpId, tmp);
            for (j = 0; j < LONG_LEN; j++) {
                output[j+offset+DIGEST_LEN] = fromTmp[j];
                output[j+offset+DIGEST_LEN+LONG_LEN] = toTmp[j];
            }
        }
    }
}

