class Point
{
   private double x;
   private double y;
   
   public Point(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   public double getX()
   {
      return x;
   }
  
   public double getY()
   {
      return y;
   }

   public double getRadius()
   {
      return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), .5);
   }

   public double getAngle()
   {
      if (x > 0) 
      { 
         return Math.atan((double) y/(double) x); 
      }
      else if (x == 0)
      {
         if (y > 0)
            return Math.PI/2;
         else if (y < 0)
            return -Math.PI/2;
         else
            return 0;
      }
      else if (y >= 0)
      {
         double val = Math.atan((double) y/(double) x);
         return val + Math.PI;           
      }
      else
      {
         double val = Math.atan((double) y/(double) x);
         return val - Math.PI;
      } 
   }

   public Point rotate90()
   {
      Point p = new Point(0, 0);
      p.x = -y;
      p.y = x;
      return p;
   }
}



 
