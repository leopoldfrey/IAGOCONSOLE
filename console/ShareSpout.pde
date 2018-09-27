import spout.*;

class ShareSpout extends ShareFrame
{
  Spout spout;

  ShareSpout()
  {
  }

  void init(PApplet parent)
  {
    spout = new Spout(parent);
  }

  void send()
  {
    spout.sendTexture();
  }
}