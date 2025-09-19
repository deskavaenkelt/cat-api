import cityCats from "@/assets/city-cats.jpg";

const CitiesSection = () => {
  const cities = [
    { name: "Stockholm", events: 142 },
    { name: "Göteborg", events: 89 },
    { name: "Malmö", events: 67 },
    { name: "Uppsala", events: 34 },
    { name: "Örebro", events: 28 }
  ];

  return (
    <section className="py-16 bg-background">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-foreground mb-4">
            Populära städer för kattälskare
          </h2>
          <p className="text-muted-foreground">
            Se vad KattKlub-organisatörer planerar i städer runt om i Sverige
          </p>
        </div>
        
        <div className="grid grid-cols-2 md:grid-cols-5 gap-6">
          {cities.map((city, index) => (
            <div 
              key={index}
              className="text-center group cursor-pointer"
            >
              <div className="relative overflow-hidden rounded-full aspect-square mb-4">
                <img 
                  src={cityCats} 
                  alt={`Katter i ${city.name}`}
                  className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                />
                <div className="absolute inset-0 bg-primary/20 group-hover:bg-primary/30 transition-colors duration-300" />
              </div>
              
              <h3 className="font-semibold text-foreground group-hover:text-primary transition-colors">
                {city.name}
              </h3>
              <p className="text-sm text-muted-foreground">
                {city.events} evenemang
              </p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default CitiesSection;