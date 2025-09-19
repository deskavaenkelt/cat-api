import { Card, CardContent, CardFooter } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Calendar, MapPin, Users } from "lucide-react";

interface EventCardProps {
  title: string;
  date: string;
  time: string;
  location: string;
  attendees: number;
  price: string;
  image: string;
  category: string;
}

const EventCard = ({ title, date, time, location, attendees, price, image, category }: EventCardProps) => {
  return (
    <Card className="overflow-hidden hover:shadow-warm transition-all duration-300 hover:-translate-y-1">
      <div className="aspect-video overflow-hidden">
        <img 
          src={image} 
          alt={title}
          className="w-full h-full object-cover hover:scale-105 transition-transform duration-300"
        />
      </div>
      
      <CardContent className="p-4 space-y-3">
        <div className="flex items-center justify-between">
          <Badge variant="secondary" className="text-xs">
            {category}
          </Badge>
          <span className="text-sm font-semibold text-primary">{price}</span>
        </div>
        
        <h3 className="font-semibold text-lg leading-tight text-foreground hover:text-primary transition-colors">
          {title}
        </h3>
        
        <div className="space-y-2 text-sm text-muted-foreground">
          <div className="flex items-center gap-2">
            <Calendar className="h-4 w-4" />
            <span>{date} • {time}</span>
          </div>
          
          <div className="flex items-center gap-2">
            <MapPin className="h-4 w-4" />
            <span>{location}</span>
          </div>
          
          <div className="flex items-center gap-2">
            <Users className="h-4 w-4" />
            <span>{attendees} deltagare</span>
          </div>
        </div>
      </CardContent>
      
      <CardFooter className="p-4 pt-0">
        <button className="w-full py-2 text-sm font-medium text-primary hover:text-primary/80 transition-colors">
          Läs mer
        </button>
      </CardFooter>
    </Card>
  );
};

export default EventCard;