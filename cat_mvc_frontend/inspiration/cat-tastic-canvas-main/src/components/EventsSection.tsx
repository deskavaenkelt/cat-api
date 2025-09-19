import { Button } from "@/components/ui/button";
import EventCard from "./EventCard.tsx";
import catCafe from "@/assets/cat-cafe.jpg";
import adoptionEvent from "@/assets/adoption-event.jpg";
import catWorkshop from "@/assets/cat-workshop.jpg";

const EventsSection = () => {
  const events = [
    {
      title: "Kattkafé & Nätverksmingel",
      date: "15 Sep",
      time: "14:00",
      location: "Stockholm Kattkafé",
      attendees: 24,
      price: "Gratis",
      image: catCafe,
      category: "Socialt"
    },
    {
      title: "Adoptionsevenemang för Katter",
      date: "18 Sep",
      time: "11:00",
      location: "Djurparken Skövde",
      attendees: 45,
      price: "Gratis",
      image: adoptionEvent,
      category: "Adoption"
    },
    {
      title: "Katthälsa Workshop",
      date: "22 Sep",
      time: "10:00",
      location: "Veterinärkliniken Göteborg",
      attendees: 12,
      price: "150 kr",
      image: catWorkshop,
      category: "Utbildning"
    }
  ];

  return (
    <section id="events" className="py-16 bg-background">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between mb-8">
          <div>
            <h2 className="text-3xl font-bold text-foreground mb-2">
              Kommande kattevenemang
            </h2>
            <p className="text-muted-foreground">
              Upptäck spännande aktiviteter för dig och din katt
            </p>
          </div>
          
          <Button variant="outline">
            Visa alla evenemang
          </Button>
        </div>
        
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {events.map((event, index) => (
            <EventCard key={index} {...event} />
          ))}
        </div>
      </div>
    </section>
  );
};

export default EventsSection;