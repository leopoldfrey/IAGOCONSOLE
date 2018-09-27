import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 
import java.util.*; 
import spout.*; 
import codeanticode.syphon.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class console extends PApplet {

//import codeanticode.syphon.*;
//import spout.*;




//int width = 600;
//int height = 300;

OscP5 oscP5;
NetAddress feedback;
int feedbackPort = 26001;
OscMessage feedChar = new OscMessage("/feedChar");
OscMessage feedString = new OscMessage("/feedString");
//ShareFrame share;

LinkedList<LogString> fifo;

LogString lastlog = new LogString();

Vector<PFont> fa;

boolean invert = false;
boolean flag = false;

int cpt = 1;
int time = 0;
int dur = 20;

int base = 0;

int maxchar = 200;

int defsize = 11;
int deffont = 0;
int margin = 30;
int hspace = 18;
int fifosize;

int defcolor = color (0, 0, 0);

int bgcolor = color (255, 255, 255);
int linecolor = color (127, 127, 127);
int prepacolor = color (0, 0, 0);

//String cursorA = "_";//(char)0;
//String cursorB = "|";//'/';//(char)32;
//boolean blink = false;

float p1, p2, p3;

public void settings() {
  fullScreen();
  //size(width, height, P3D);  
  PJOGL.profile=1;
}

public void setup()
{
  fa = new Vector<PFont>();

  fa.add(createFont("Menlo-Regular", 11));
  fa.add(createFont("CourierNewPSMT", 50));
  fa.add(createFont("Avenir-Roman", 50));
  fa.add(createFont("CenturyGothic", 50));
  fa.add(createFont("Verdana", 50));
  fa.add(createFont("SourceCodePro-Bold", 50));
  fa.add(createFont("PingFangTC-Light", 50));
  fa.add(createFont("PTSans-Caption", 50));
  fa.add(createFont("PTMono-Bold", 50));
  fa.add(createFont("OratorStd", 50));
  fa.add(createFont("Optima-Bold", 50));
  fa.add(createFont("MyriadPro-Cond", 50));
  fa.add(createFont("Monaco", 50));
  fa.add(createFont("LetterGothicStd-Bold", 50));
  fa.add(createFont("Lucida Sans Typewriter Regular", 50));
  fa.add(createFont("KozGoPro-Light", 50));
  fa.add(createFont("Krungthep", 50));
  fa.add(createFont("HelveticaNeue-Light", 50));
  fa.add(createFont("Helvetica-Light", 50));
  fa.add(createFont("GillSans", 50));
  fa.add(createFont("GurmukhiMN", 50));
  fa.add(createFont("Futura-Medium", 50));
  fa.add(createFont("CourierNewPSMT", 50));
  fa.add(createFont("AvenirNext-Regular", 50));
  fa.add(createFont("AppleGothic", 50));
  fa.add(createFont("AnonymousPro", 50));

  textFont(fa.get(0));
  textAlign(LEFT, CENTER);

  oscP5 = new OscP5(this, 26000);
  feedback = new NetAddress("192.168.1.100", feedbackPort);

  frameRate(30);
  time = millis();
  fifo = new LinkedList<LogString>();
  fifosize = (int)((height - margin)/hspace);
  for (int i = 0; i < fifosize - 1; i++)
  {
    LogString ls = new LogString();
    ls.setString(" ");
    fifo.add(ls);
  }

  /*if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)
   share = new ShareSyphon();
   else
   share = new ShareSpout();
   share.init(this);//*/

  String[] fontList = PFont.list();
  //printArray(fontList);
  for (int i = 0; i < fontList.length; i++)
  {
    if (fontList[i].startsWith("Menlo"))
      println(fontList[i]);
    //delay(200);
  }//*/
}

public void draw() {
  //blink = !blink;
  /*if (!flag)
   {
   flag = true;
   surface.setLocation(20, 500);
   }//*/

  pushMatrix();

  if (invert)
    background(255, 0, 0, 255);
  else
    background(bgcolor, 0);//*/

  if (fifo.size() != 0)
  {
    int hh = base;
    int ww = 20;
    try
    {
      for (int i = 0; i < fifo.size(); i++)
      {
        LogString ls = fifo.get(i);
        //ls.print();
        String s = ls.str;
        setFont(ls.font);
        textSize(ls.size);
        fill(ls.col, (i*1.f/(fifosize/2))*255);
        //hh += ls.size + hspace;
        //println(hh);
        // MOTION
        if (ls.cpt < s.length())
        {
          //if(ls.cpt == 1)
          //oscP5.send(feedString, feedback);

          if(ls.cpt >= 0)
            text(s.substring(0, ls.cpt), ww, i * hspace + 30);
          if (millis() - ls.time > dur)
          {
            ls.time = millis();
            ls.cpt++;
            //oscP5.send(feedChar, feedback);
          }
        } else {
          text(s, ww, i * hspace + 30);
        }
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /*
  stroke(linecolor);
   line(10, 10, width-10, 10);
   line(width-10, 10, width-10, height-10);
   line(width-10, height-10, 10, height-10);
   line(10, height-10, 10, 10);
   
   
   beginShape(QUADS);
   fill(bgcolor, 255);
   vertex(10, 10);
   fill(bgcolor, 255);
   vertex(width-10, 10);
   fill(bgcolor, 0);
   vertex(width-10, height-10);
   fill(bgcolor, 0);
   vertex(10, height-10);
   endShape();//*/

  popMatrix();
  //share.send();
}

public void setFont(int font)
{
  if (font < fa.size() && font >= 0)
  {  
    textFont(fa.get(font));
    //println(fa.get(font).getName());
  } else
    textFont(fa.get(0));
}

public void keyPressed() {
  if (key == 'I') {
    invert = !invert;
  } else if (key == 'C') {
    clear();
  }
}

public void clear()
{
  fifo.clear();
  for (int i = 0; i < fifosize - 1; i++)
  {
    LogString ls = new LogString();
    ls.setString(" ");
    fifo.add(ls);
  }
}

public void mousePressed()
{
}

public void oscEvent(OscMessage theOscMessage)
{
  if (theOscMessage.checkAddrPattern("/clear")==true)
  {
    clear();
  } else if (theOscMessage.checkAddrPattern("/display")==true) {
    byte ptext[] = theOscMessage.getBytes();
    try
    {
      String s = new String(ptext, "UTF-8");
      s = s.substring(s.indexOf(",s")+4);
      if (s.length() > maxchar)
      {
        Vector lsV = new Vector<LogString>();
        while (s.length() > maxchar)
        {
          LogString ls = new LogString();
          ls.setString(s.substring(0, maxchar-1));
          ls.setCpt(maxchar);
          ls.init();
          ls.setSize(defsize);
          ls.setFont(deffont);
          ls.setColor(prepacolor);
          lsV.add(ls);
          s = s.substring(maxchar-1);
        }

        LogString ls = new LogString();
        ls.setString(s);
        ls.setCpt(s.length());
        ls.init();
        ls.setSize(defsize);
        ls.setFont(deffont);
        ls.setColor(prepacolor);
        lastlog = ls;
        lsV.add(ls);

        for (int i = 0; i < lsV.size(); i++)
        {
          fifo.add((LogString)lsV.get(i));
          if (fifo.size() > fifosize)
            fifo.poll();
        }
      } else {
        LogString ls = new LogString();
        ls.setString(s);
        ls.setCpt(1);
        ls.init();
        ls.setSize(defsize);
        ls.setFont(deffont);
        ls.setColor(prepacolor);
        lastlog = ls;
        fifo.add(ls);
        if (fifo.size() > fifosize)
          fifo.poll();
      }
    } 
    catch (Exception e) {
    }
  } else if (theOscMessage.checkAddrPattern("/last")==true) {
    byte ptext[] = theOscMessage.getBytes();
    String s = new String(ptext);//, "UTF_8");
    s = s.substring(s.indexOf(",s")+4);
    lastlog.setString(s);
    lastlog.setCpt(s.length());
  } else if (theOscMessage.checkAddrPattern("/size")==true) {
    if (theOscMessage.checkTypetag("i")) {
      lastlog.setSize(theOscMessage.get(0).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/font")==true) {
    if (theOscMessage.checkTypetag("i")) {
      lastlog.setFont(theOscMessage.get(0).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/prepacolor")==true) {
    if (theOscMessage.checkTypetag("iii")) {
      prepacolor = color(theOscMessage.get(0).intValue(), theOscMessage.get(1).intValue(), theOscMessage.get(2).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/color")==true) {
    if (theOscMessage.checkTypetag("iii")) {
      lastlog.setColor(theOscMessage.get(0).intValue(), theOscMessage.get(1).intValue(), theOscMessage.get(2).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/bgcolor")==true) {
    if (theOscMessage.checkTypetag("iii")) {
      bgcolor = color(theOscMessage.get(0).intValue(), theOscMessage.get(1).intValue(), theOscMessage.get(2).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/linecolor")==true) {
    if (theOscMessage.checkTypetag("iii")) {
      linecolor = color(theOscMessage.get(0).intValue(), theOscMessage.get(1).intValue(), theOscMessage.get(2).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/invert")==true) {
    if (theOscMessage.checkTypetag("i")) {
      invert = theOscMessage.get(0).intValue() > 0;
    }
  } else if (theOscMessage.checkAddrPattern("/fifosize")==true) {
    if (theOscMessage.checkTypetag("i")) {
      fifosize = theOscMessage.get(0).intValue();
    }
  } else if (theOscMessage.checkAddrPattern("/speed")==true) {
    if (theOscMessage.checkTypetag("i")) {
      dur = theOscMessage.get(0).intValue();
    }
  } else if (theOscMessage.checkAddrPattern("/defsize")==true) {
    if (theOscMessage.checkTypetag("i")) {
      defsize = theOscMessage.get(0).intValue();
    }
  } else if (theOscMessage.checkAddrPattern("/defcolor")==true) {
    if (theOscMessage.checkTypetag("iii")) {
      defcolor = color(theOscMessage.get(0).intValue(), theOscMessage.get(1).intValue(), theOscMessage.get(2).intValue());
    }
  } else if (theOscMessage.checkAddrPattern("/deffont")==true) {
    if (theOscMessage.checkTypetag("i")) {
      deffont = theOscMessage.get(0).intValue();
    }
  } else if (theOscMessage.checkAddrPattern("/p1")==true) {
    if (theOscMessage.checkTypetag("f")) {
      p1 = theOscMessage.get(0).floatValue();
    }
  } else if (theOscMessage.checkAddrPattern("/p2")==true) {
    if (theOscMessage.checkTypetag("f")) {
      p2 = theOscMessage.get(0).floatValue();
    }
  } else if (theOscMessage.checkAddrPattern("/p3")==true) {
    if (theOscMessage.checkTypetag("f")) {
      p3 = theOscMessage.get(0).floatValue();
    }
  } else if (theOscMessage.checkAddrPattern("/base")==true) {
    if (theOscMessage.checkTypetag("i")) {
      base = theOscMessage.get(0).intValue();
    }
  }
}

class LogString
{
  public String str;
  public int font = 0;
  public int size = 15;
  public int col = color(255,255,255);
  int cpt = 1;
  int time = 0;
   
  LogString()
  {
  }
  
  public void init()
  {
   time = millis();   
  }
  
  public void setString(String s)
  {
    str = s;
  }
  
  public void setFont(int f)
  {
    font = f;
  }
  
  public void setSize(int s)
  {
    size = s;
  }
  
  public void setColor(int c)
  {
    col = c;
  }
  
  public void setColor(int r, int g, int b)
  {
    col = color(r,g,b);
  }
  
  public void setCpt(int c)
  {
    cpt = c;
  }
  
  public void inc()
  {
    if(cpt < str.length())
      cpt++;
  }
  
  public void print()
  {
    println("____");
    println(str);
    println(size);
    println(font);
    println(col);
  }
}
abstract class ShareFrame
{
  ShareFrame()
  {
  }
  
  public abstract void init(PApplet parent);
  
  public abstract void send();
}


class ShareSpout extends ShareFrame
{
  Spout spout;

  ShareSpout()
  {
  }

  public void init(PApplet parent)
  {
    spout = new Spout(parent);
  }

  public void send()
  {
    spout.sendTexture();
  }
}


class ShareSyphon extends ShareFrame
{
  SyphonServer syphon;

  ShareSyphon()
  {
  }

  public void init(PApplet parent)
  {
    syphon = new SyphonServer(parent, "SoftConsole");
  }

  public void send()
  {
    syphon.sendScreen();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050505", "--hide-stop", "console" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
