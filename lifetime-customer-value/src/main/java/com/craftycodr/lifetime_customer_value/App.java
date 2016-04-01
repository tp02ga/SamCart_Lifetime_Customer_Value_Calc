package com.craftycodr.lifetime_customer_value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class App
{
  private static final String CHARGED = "charged";
  // private static final Double AVG_CODERS_CAMPUS = 36.81;
  // private static final Double
  // AVG_CODERS_CAMPUS_YEARLY_UPSELL_30_PERCENT_DISCOUNT = 283.50;
  // private static final Double AVG_CODERS_CAMPUS_YEARLY_UPSELL = 344.25;
  // private static final Double AVG_DEDICATED_SUBSCRIPTION = 45.00;
  // private static final Double AVG_DEDICATED_PLAN_FROM_WEBINAR = 33.75;

  Map<String, List<OrderDetails>> map = new HashMap<>();
  // Map<Integer, Double> prices = new HashMap<>();
  List<Integer> yearlyPlans = new ArrayList<>();
  List<Integer> monthlyPlans = new ArrayList<>();
  Set<String> possibleOvercharging = new HashSet<>();
  Set<String> peopleOnYearlyPlans = new HashSet<>();

  public static void main(String[] args) throws IOException
  {
    App obj = new App();
    obj.run();

  }

  public void run() throws IOException
  {
    // prices.put(16637, 25.00);
    // prices.put(16638, AVG_CODERS_CAMPUS);
    // prices.put(16908, AVG_CODERS_CAMPUS_YEARLY_UPSELL);
    // prices.put(16911, AVG_CODERS_CAMPUS_YEARLY_UPSELL_30_PERCENT_DISCOUNT);
    // prices.put(18388, AVG_CODERS_CAMPUS_YEARLY_UPSELL);
    // prices.put(19598, AVG_DEDICATED_SUBSCRIPTION);
    // prices.put(22093, AVG_DEDICATED_PLAN_FROM_WEBINAR);

    yearlyPlans.add(16908);
    yearlyPlans.add(16910);
    yearlyPlans.add(16911);
    yearlyPlans.add(18388);

    monthlyPlans.add(16637);
    monthlyPlans.add(16638);
    monthlyPlans.add(19598);
    monthlyPlans.add(22093);

    
    String rootPath = "C:\\Users\\thecy\\Downloads\\";
    List<Path> reportsList = Files.walk(Paths.get(rootPath))
    .filter(Files::isRegularFile)
    .collect(Collectors.toList());
    
    List<ReportFile> reports = new ArrayList<>();
    
    for (Path path : reportsList)
    {
      if (path.getFileName().toString().startsWith("tmp_report"))
      {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = attributes.creationTime();
        reports.add(new ReportFile(path, creationTime));
      }
    }
    
    Collections.sort(reports);
    
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";

    try
    {

      br = new BufferedReader(new FileReader(reports.get(0).getPath().toFile()));
      while ((line = br.readLine()) != null)
      {
        // use comma as separator
        String[] lines = line.split(csvSplitBy);

        OrderDetails details = new OrderDetails(lines);
        if (details.getOrderId() != null)
        {
          if (map.containsKey(details.getEmailAddress()))
          {
            List<OrderDetails> list = map.get(details.getEmailAddress());
            list.add(details);
          } else
          {
            List<OrderDetails> list = new ArrayList<>();
            list.add(details);
            map.put(details.getEmailAddress(), list);
          }
        }
      }

    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    } finally
    {
      if (br != null)
      {
        try
        {
          br.close();
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }

    int customerCount = 0;
    double totalPrice = 0.0;
    double totalNumPaidMonths = 0;

    for (Map.Entry<String, List<OrderDetails>> entry : map.entrySet())
    {
      if (isTrevor(entry.getKey()))
      {
        continue;
      }
      customerCount++;
      boolean yearlyFlag = false;
      boolean monthlyFlag = false;

      for (OrderDetails detail : entry.getValue())
      {
        if (detail.getOrderStatus().equalsIgnoreCase(CHARGED))
        {
          if (yearlyPlans.contains(detail.getProductId()))
          {
            yearlyFlag = true;
            totalNumPaidMonths += 12;
            peopleOnYearlyPlans.add(entry.getKey());
          } else
          {
            Calendar orderDate = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            orderDate.setTime(detail.getOrderDate());
            if (orderDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
              monthlyFlag = true;

            totalNumPaidMonths++;
          }
          totalPrice += detail.getPrice();
        }
      }
      if (monthlyFlag && yearlyFlag)
      {
        possibleOvercharging.add(entry.getKey());

      }
    }
    System.out.println("---- START People On Yearly Plans ----");
    for (String email : peopleOnYearlyPlans)
    {
      System.out.println(email);
    }
    System.out.println("----- END People On Yearly Plans -----");
    System.out.println();
    System.out.println("---- START Possible Overcharging List ----");
    System.out.println("Check that you're not charging the following people both monthly AND yearly... ");
    for (String email : possibleOvercharging)
    {
      System.out.println(email);
    }
    System.out.println("----- END Possible Overcharging List -----");
    System.out.println();
    System.out.println("Total Customers: " + customerCount + ", total charged: " + totalPrice + ", total months paid: "
        + totalNumPaidMonths);
    System.out.println("Customer Value: " + Math.round((totalPrice / customerCount) * 100.0) / 100.0
        + ", Avg months paid: " + Math.round((totalNumPaidMonths / customerCount) * 100.0) / 100.0);
  }

  private boolean isTrevor(String key)
  {
    if (key.equalsIgnoreCase("tpage@ecosim.ca"))
    {
      return true;
    } else if (key.equalsIgnoreCase("trevor@craftycodr.com"))
    {
      return true;
    } else if (key.equalsIgnoreCase("trevor.page@ymail.com"))
    {
      return true;
    }
    return false;
  }
}
