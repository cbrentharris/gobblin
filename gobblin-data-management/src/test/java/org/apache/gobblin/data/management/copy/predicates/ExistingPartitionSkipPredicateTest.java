package org.apache.gobblin.data.management.copy.predicates;

import com.google.common.base.Optional;
import org.apache.gobblin.data.management.copy.hive.HivePartitionFileSet;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.junit.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;


public class ExistingPartitionSkipPredicateTest {
  ExistingPartitionSkipPredicate predicate = new ExistingPartitionSkipPredicate();

  @Test
  public void shouldSkipHiveDatasetWithExistingPartition() {
    HivePartitionFileSet fileSetWithExistingPartition = mock(HivePartitionFileSet.class);
    HivePartitionFileSet fileSetWithoutExistingPartition = mock(HivePartitionFileSet.class);
    Partition partition = mock(Partition.class);
    when(fileSetWithExistingPartition.getExistingTargetPartition()).thenReturn(Optional.of(partition));
    when(fileSetWithoutExistingPartition.getExistingTargetPartition()).thenReturn(Optional.absent());
    Assert.assertTrue(predicate.apply(fileSetWithExistingPartition));
    Assert.assertFalse(predicate.apply(fileSetWithoutExistingPartition));
  }
}
