import { Heart, Users, BookOpen, Stethoscope, Camera, Coffee, Home, Gift } from "lucide-react";

const CategoriesSection = () => {
  const categories = [
    {
      icon: Heart,
      name: "Adoption & Räddning",
      color: "text-red-500"
    },
    {
      icon: Users,
      name: "Sociala aktiviteter",
      color: "text-blue-500"
    },
    {
      icon: BookOpen,
      name: "Utbildning & Tips",
      color: "text-green-500"
    },
    {
      icon: Stethoscope,
      name: "Hälsa & Vård",
      color: "text-purple-500"
    },
    {
      icon: Camera,
      name: "Fotografering",
      color: "text-pink-500"
    },
    {
      icon: Coffee,
      name: "Kattkafén",
      color: "text-amber-500"
    },
    {
      icon: Home,
      name: "Hembesök & Stöd",
      color: "text-teal-500"
    },
    {
      icon: Gift,
      name: "Evenemang & Fester",
      color: "text-orange-500"
    }
  ];

  return (
    <section className="py-16 bg-gradient-soft">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-foreground mb-4">
            Utforska kategorier
          </h2>
          <p className="text-muted-foreground max-w-2xl mx-auto">
            Hitta aktiviteter som passar dig och din katts intressen och behov
          </p>
        </div>
        
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 md:gap-6">
          {categories.map((category, index) => {
            const IconComponent = category.icon;
            return (
              <div 
                key={index}
                className="flex flex-col items-center p-6 bg-card rounded-lg hover:shadow-soft transition-all duration-300 hover:-translate-y-1 cursor-pointer group"
              >
                <div className={`p-3 rounded-full bg-secondary mb-4 group-hover:scale-110 transition-transform duration-300`}>
                  <IconComponent className={`h-6 w-6 ${category.color}`} />
                </div>
                <h3 className="text-sm font-medium text-center text-foreground">
                  {category.name}
                </h3>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default CategoriesSection;