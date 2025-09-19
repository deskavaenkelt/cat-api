import { Button } from "@/components/ui/button";
import cityCats from "@/assets/city-cats.jpg";

const JoinSection = () => {
  return (
    <section className="py-16 bg-gradient-warm">
      <div className="container mx-auto px-4">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          <div className="space-y-6">
            <h2 className="text-3xl md:text-4xl font-bold text-primary-foreground">
              Gå med i KattKlub
            </h2>
            
            <p className="text-lg text-primary-foreground/90 leading-relaxed">
              Människor använder KattKlub för att träffa nya personer, lära sig nya saker, 
              hitta stöd, komma ut ur sina komfortzoner och sträva efter sina passioner tillsammans. 
              Medlemskap är kostnadsfritt.
            </p>
            
            <Button variant="secondary" size="lg" className="font-semibold">
              Registrera dig
            </Button>
          </div>
          
          <div className="relative">
            <img 
              src={cityCats} 
              alt="Gemenskap av kattälskare" 
              className="w-full h-auto rounded-lg shadow-2xl"
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default JoinSection;