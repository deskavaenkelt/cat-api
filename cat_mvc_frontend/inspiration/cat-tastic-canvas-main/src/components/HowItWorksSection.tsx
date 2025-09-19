import { Search, Plus, Heart } from "lucide-react";

const HowItWorksSection = () => {
  const steps = [
    {
      icon: Search,
      title: "Upptäck evenemang och grupper",
      description: "Sök bland lokala kattevenemang för allt du älskar"
    },
    {
      icon: Plus,
      title: "Starta en grupp eller värd evenemang",
      description: "Skapa din egen KattKlub-grupp och dra till dig en gemenskap av miljontals kattälskare"
    },
    {
      icon: Heart,
      title: "Vänskap skapas på KattKlub",
      description: "Sedan 2020 har kattälskare använt KattKlub för att träffa nya vänner, lära sig nya saker och utveckla sin kärlek till katter"
    }
  ];

  return (
    <section className="py-16 bg-gradient-soft">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-foreground mb-4">
            Så fungerar KattKlub
          </h2>
        </div>
        
        <div className="grid md:grid-cols-3 gap-8">
          {steps.map((step, index) => {
            const IconComponent = step.icon;
            return (
              <div key={index} className="text-center space-y-4">
                <div className="flex justify-center">
                  <div className="p-4 bg-primary rounded-full">
                    <IconComponent className="h-8 w-8 text-primary-foreground" />
                  </div>
                </div>
                
                <h3 className="text-xl font-semibold text-foreground">
                  {step.title}
                </h3>
                
                <p className="text-muted-foreground leading-relaxed">
                  {step.description}
                </p>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default HowItWorksSection;