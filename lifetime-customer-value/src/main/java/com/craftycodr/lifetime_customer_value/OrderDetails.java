package com.craftycodr.lifetime_customer_value;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDetails
{
  private static final List<Integer> SUBSCRIPTION_PRODUCT_IDS = Arrays.asList(16908, 16910, 18388, 16911, 16638, 22093,
      19598, 16637);
  private Integer orderId;
  private Date orderDate;
  private String orderTime;
  private String orderStatus;
  private Integer productId;
  private String productName;
  private Double price;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String phoneNumber;
  private String billingAddressLine1;
  private String billingAddressLine2;
  private String billingAddressCity;
  private String billingAddressState;
  private String billingAddressZip;

  public OrderDetails(String[] lines)
  {
    for (int i = 0; i<lines.length; i++)
    {
      lines[i] = lines[i].replace("\"", "");
    }
    
    if (lines[0].equalsIgnoreCase("Order ID"))
    {
      assert (lines[1].equalsIgnoreCase("Order Date"));
      assert (lines[2].equalsIgnoreCase("Order Time"));
      assert (lines[3].equalsIgnoreCase("Order Status"));
      assert (lines[4].equalsIgnoreCase("Product ID"));
      assert (lines[5].equalsIgnoreCase("Product Name"));
      assert (lines[6].equalsIgnoreCase("Price"));
      assert (lines[7].equalsIgnoreCase("Customer First Name"));
      assert (lines[8].equalsIgnoreCase("Customer Last Name"));
      assert (lines[9].equalsIgnoreCase("Customer Email"));
      assert (lines[10].equalsIgnoreCase("Customer Phone Number"));
      assert (lines[11].equalsIgnoreCase("Billing Address Line 1"));
      assert (lines[12].equalsIgnoreCase("Billing Address Line 2"));
      assert (lines[13].equalsIgnoreCase("Billing Address City"));
      assert (lines[14].equalsIgnoreCase("Billing Address State"));
      assert (lines[15].equalsIgnoreCase("Billing Address Zip"));
    } else
    {
      Integer productId = Integer.valueOf(lines[4]);
      if (SUBSCRIPTION_PRODUCT_IDS.contains(productId))
      {
        this.orderId = Integer.valueOf(lines[0]);
        Calendar cal = Calendar.getInstance();
        String[] dateSplit = lines[1].split("/");
        cal.set(Integer.valueOf(dateSplit[2]), Integer.valueOf(dateSplit[0]) - 1, Integer.valueOf(dateSplit[1]));

        this.orderDate = cal.getTime();
        this.orderTime = lines[2];
        this.orderStatus = lines[3];
        this.productId = Integer.valueOf(lines[4]);
        this.productName = lines[5];
        this.price = Double.valueOf(lines[6].substring(1));
        this.firstName = lines[7];
        this.lastName = lines[8];
        this.emailAddress = lines[9];
        try
        {
          this.phoneNumber = lines[10];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {
          this.billingAddressLine1 = lines[11];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {
          this.billingAddressLine2 = lines[12];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {
          this.billingAddressCity = lines[13];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {
          this.billingAddressState = lines[14];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {
          this.billingAddressZip = lines[15];
        } catch (ArrayIndexOutOfBoundsException e)
        {
        }
      }

    }

  }

  public Integer getOrderId()
  {
    return orderId;
  }

  public void setOrderId(Integer orderId)
  {
    this.orderId = orderId;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public void setOrderDate(Date orderDate)
  {
    this.orderDate = orderDate;
  }

  public String getOrderTime()
  {
    return orderTime;
  }

  public void setOrderTime(String orderTime)
  {
    this.orderTime = orderTime;
  }

  public String getOrderStatus()
  {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus)
  {
    this.orderStatus = orderStatus;
  }

  public Integer getProductId()
  {
    return productId;
  }

  public void setProductId(Integer productId)
  {
    this.productId = productId;
  }

  public String getProductName()
  {
    return productName;
  }

  public void setProductName(String productName)
  {
    this.productName = productName;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getBillingAddressLine1()
  {
    return billingAddressLine1;
  }

  public void setBillingAddressLine1(String billingAddressLine1)
  {
    this.billingAddressLine1 = billingAddressLine1;
  }

  public String getBillingAddressLine2()
  {
    return billingAddressLine2;
  }

  public void setBillingAddressLine2(String billingAddressLine2)
  {
    this.billingAddressLine2 = billingAddressLine2;
  }

  public String getBillingAddressCity()
  {
    return billingAddressCity;
  }

  public void setBillingAddressCity(String billingAddressCity)
  {
    this.billingAddressCity = billingAddressCity;
  }

  public String getBillingAddressState()
  {
    return billingAddressState;
  }

  public void setBillingAddressState(String billingAddressState)
  {
    this.billingAddressState = billingAddressState;
  }

  public String getBillingAddressZip()
  {
    return billingAddressZip;
  }

  public void setBillingAddressZip(String billingAddressZip)
  {
    this.billingAddressZip = billingAddressZip;
  }

  @Override
  public String toString()
  {
    return "OrderDetails [orderStatus=" + orderStatus + ", productName=" + productName + ", price=" + price
        + ", emailAddress=" + emailAddress + "]";
  }

}
