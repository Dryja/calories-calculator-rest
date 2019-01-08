package com.testing.calories.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

import static com.testing.calories.calculator.util.Calculator.*;

public class CalculatorBenchmark {

  /*
   * Mode.Throughput, as stated in its Javadoc, measures the raw throughput by
   * continuously calling the benchmark method in a time-bound iteration, and
   * counting how many times we executed the method.
   *
   * We are using the special annotation to select the units to measure in,
   * although you can use the default.
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void measureThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void measureThroughputCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void measureThroughputChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }
  /*
   * Mode.AverageTime measures the average execution time, and it does it
   * in the way similar to Mode.Throughput.
   *
   * Some might say it is the reciprocal throughput, and it really is.
   * There are workloads where measuring times is more convenient though.
   */

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAvgTimeThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAvgTimeCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAvgTimeChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }

  /*
   * Mode.SampleTime samples the execution time. With this mode, we are
   * still running the method in a time-bound iteration, but instead of
   * measuring the total time, we measure the time spent in *some* of
   * the benchmark method calls.
   *
   * This allows us to infer the distributions, percentiles, etc.
   *
   * JMH also tries to auto-adjust sampling frequency: if the method
   * is long enough, you will end up capturing all the samples.
   */
  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSamplesThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSamplesCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSamplesChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }

  /*
   * Mode.SingleShotTime measures the single method invocation time. As the Javadoc
   * suggests, we do only the single benchmark method invocation. The iteration
   * time is meaningless in this mode: as soon as benchmark method stops, the
   * iteration is over.
   *
   * This mode is useful to do cold startup tests, when you specifically
   * do not want to call the benchmark method continuously.
   */
  @Benchmark
  @BenchmarkMode(Mode.SingleShotTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSingleShotThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode(Mode.SingleShotTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSingleShotCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode(Mode.SingleShotTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureSingleShotChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }

  /*
   * We can also ask for multiple benchmark modes at once. All the tests
   * above can be replaced with just a single test like this:
   */
  @Benchmark
  @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureMultipleThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureMultipleCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureMultipleChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }

  /*
   * Or even...
   */

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAllThroughputCalculateEnergyRequirements() {
    calculateEnergyRequirements(1000.0, "sedentary");
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAllCalculateBMR() {
    calculateBMR("male", 100, 50, 30);
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void measureAllChooseEnergyOption() {
    chooseEnergyOption("gain", 3000.0);
  }

}
