package com.djd.fun.tachchapter.demo014swing.maze.models;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.djd.fun.tachchapter.demo014swing.maze.models.Floor;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

/**
 * @author JGD
 * @since 8/15/16
 */
public class FloorFactory {

  private final ObjectReader reader;

  public FloorFactory() {
    this.reader = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY).readerFor(String[].class);
  }

  public Floor loadFloor(String floorId) {
    try {
      URL url = Resources.getResource("floors/floor-" + floorId + ".csv");
      MappingIterator<String[]> values = reader.readValues(url);
      List<String[]> listOfRows = values.readAll();
      String[][] rows = listOfRows.toArray(new String[0][0]);
      return new Floor(rows);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }
}
