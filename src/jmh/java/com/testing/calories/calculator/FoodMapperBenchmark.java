package com.testing.calories.calculator;

import com.testing.calories.calculator.dto.FoodDTO;
import com.testing.calories.calculator.dtoMapper.FoodMapper;
import com.testing.calories.calculator.model.FoodEntity;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class FoodMapperBenchmark {
  private FoodEntity food;
  private FoodDTO foodDTO;

  @Setup
  public void setup() {
    food = FoodEntity.builder().calories(1000L).name("test").build();
    foodDTO = FoodDTO.builder().calories(1000L).name("test").build();
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAllToDTO() {
    FoodMapper.toDTO(food);
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAllToEntity() {
    FoodMapper.toEntity(foodDTO);
  }
}
