package com.craftycodr.lifetime_customer_value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     */
    public void testApp() throws IOException
    {
      String rootPath = "C:\\Users\\thecy\\Downloads\\";
      List<Path> list = Files.walk(Paths.get(rootPath))
      .filter(Files::isRegularFile)
      .collect(Collectors.toList());
      
      List<ReportFile> reports = new ArrayList<>();
      
      for (Path path : list)
      {
        if (path.getFileName().toString().startsWith("tmp_report"))
        {
          BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
          FileTime creationTime = attributes.creationTime();
          reports.add(new ReportFile(path, creationTime));
        }
      }
      
      Collections.sort(reports);
      
      for (ReportFile file : reports)
      {
        System.out.println(file);
      }
    }
}
