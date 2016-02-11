package com.craftycodr.lifetime_customer_value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
  private static final String CHARGED = "charged";
  private static final Double AVG_CODERS_CAMPUS = 36.81;
  private static final Double AVG_CODERS_CAMPUS_YEARLY_UPSELL_30_PERCENT_DISCOUNT = 283.50;
  private static final Double AVG_CODERS_CAMPUS_YEARLY_UPSELL = 344.25;
  private static final Double AVG_DEDICATED_SUBSCRIPTION = 45.00;
  private static final Double AVG_DEDICATED_PLAN_FROM_WEBINAR = 33.75;
  
  Map<String, List<OrderDetails>> map = new HashMap<>();
  Map<Integer, Double> prices = new HashMap<>();
  
  public static void main(String[] args)
  {
    App obj = new App();
    obj.run();

  }

  public void run()
  {
    prices.put(16637, 25.00);
    prices.put(16638, AVG_CODERS_CAMPUS);
    prices.put(16908, AVG_CODERS_CAMPUS_YEARLY_UPSELL);
    prices.put(16911, AVG_CODERS_CAMPUS_YEARLY_UPSELL_30_PERCENT_DISCOUNT);
    prices.put(18388, AVG_CODERS_CAMPUS_YEARLY_UPSELL);
    prices.put(19598, AVG_DEDICATED_SUBSCRIPTION);
    prices.put(22093, AVG_DEDICATED_PLAN_FROM_WEBINAR);
    
    String csvFile = "C:\\Users\\thecy\\Desktop\\tmp_report.csv";
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";

    try
    {

      br = new BufferedReader(new FileReader(csvFile));
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
          }
          else
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
    
    for (Map.Entry<String, List<OrderDetails>> entry : map.entrySet())
    {
      if (isTrevor(entry.getKey()))
      {
        continue;
      }
      customerCount++;
      System.out.println(entry.getKey());
      for (OrderDetails detail : entry.getValue())
      {
        if (detail.getOrderStatus().equalsIgnoreCase(CHARGED))
        {
          totalPrice += prices.get(detail.getProductId());
        }
      }
      System.out.println("Total Customers: " + customerCount + ", total charged: " + totalPrice);
    }
  }

  private boolean isTrevor(String key)
  {
    if (key.equalsIgnoreCase("tpage@ecosim.ca"))
    {
      return true;
    }
    else if (key.equalsIgnoreCase("trevor@craftycodr.com"))
    {
      return true;
    }
    else if (key.equalsIgnoreCase("trevor.page@ymail.com"))
    {
      return true;
    }
    return false;
  }
}
