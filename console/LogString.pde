class LogString
{
  public String str;
  public int font = 0;
  public int size = 15;
  public color col = color(255,255,255);
  int cpt = 1;
  int time = 0;
   
  LogString()
  {
  }
  
  void init()
  {
   time = millis();   
  }
  
  void setString(String s)
  {
    str = s;
  }
  
  void setFont(int f)
  {
    font = f;
  }
  
  void setSize(int s)
  {
    size = s;
  }
  
  void setColor(color c)
  {
    col = c;
  }
  
  void setColor(int r, int g, int b)
  {
    col = color(r,g,b);
  }
  
  void setCpt(int c)
  {
    cpt = c;
  }
  
  void inc()
  {
    if(cpt < str.length())
      cpt++;
  }
  
  void print()
  {
    println("____");
    println(str);
    println(size);
    println(font);
    println(col);
  }
}