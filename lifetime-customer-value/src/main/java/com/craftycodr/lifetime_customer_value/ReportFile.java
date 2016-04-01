package com.craftycodr.lifetime_customer_value;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class ReportFile implements Comparable<ReportFile>
{
  private Path path;
  private FileTime created;
  
  public ReportFile (Path path, FileTime created)
  {
    this.path = path;
    this.created = created;
  }
  
  public Path getPath()
  {
    return path;
  }
  public void setPath(Path path)
  {
    this.path = path;
  }
  public FileTime getCreated()
  {
    return created;
  }
  public void setCreated(FileTime created)
  {
    this.created = created;
  }
  @Override
  public int compareTo(ReportFile o)
  {
    return o.getCreated().compareTo(this.getCreated());
  }

  @Override
  public String toString()
  {
    return "ReportFile [path=" + path + ", created=" + created + "]";
  }
}
