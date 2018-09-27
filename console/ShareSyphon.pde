import codeanticode.syphon.*;

class ShareSyphon extends ShareFrame
{
  SyphonServer syphon;

  ShareSyphon()
  {
  }

  void init(PApplet parent)
  {
    syphon = new SyphonServer(parent, "SoftConsole");
  }

  void send()
  {
    syphon.sendScreen();
  }
}