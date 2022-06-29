package org.apache.gobblin.data.management.copy.predicates;

import avro.shaded.com.google.common.base.Predicate;
import org.apache.gobblin.data.management.copy.hive.HivePartitionFileSet;
import org.jetbrains.annotations.Nullable;


/**
 * This skip predicate will skip any partition that's already registered in the destination
 * hive table.
 */
public class ExistingPartitionSkipPredicate implements Predicate<HivePartitionFileSet> {
  @Override
  public boolean apply(@Nullable HivePartitionFileSet input) {
    if (input == null) {
      return true;
    }

    return input.getExistingTargetPartition().isPresent();
  }
}
