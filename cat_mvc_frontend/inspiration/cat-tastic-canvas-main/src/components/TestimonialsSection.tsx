import { Card, CardContent } from "@/components/ui/card";

const TestimonialsSection = () => {
  const testimonials = [
    {
      name: "Anna Karlsson",
      story: "Jag använde KattKlub för att hitta vänner med nya katter",
      description: "Som ny kattägare ville jag träffa vänner med erfarna kattmammor. Genom att gå med i en KattKlub-grupp fick jag ovärderliga råd och flera livslånga vänskap.",
      image: "👩‍🦰"
    },
    {
      name: "Erik Lindberg", 
      story: "Hur man förvandlar tillfälliga bekantskaper till nära vänskap",
      description: "Det är bevisat att kattsamhällen påverkar mental hälsa positivt. Här är vad du kan göra för att fördjupa relationer genom gemensamma kattintressen.",
      image: "👨‍🦲"
    },
    {
      name: "Maria Svensson",
      story: "Har du rätt antal kattvänner?",
      description: "Studier från hela världen har försökt att hjälpa människor att svara på de tre stora kattfrågorna: Hur många katter ska man ha? Vad ska man döpa dem till? Och hur träffar man andra kattälskare?",
      image: "👩‍🦱"
    }
  ];

  return (
    <section className="py-16 bg-background">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-foreground mb-4">
            Vänskap skapas på KattKlub
          </h2>
          <p className="text-muted-foreground max-w-3xl mx-auto">
            Sedan 2020 har kattälskare använt KattKlub för att träffa nya vänner, träffa kattexpermänniskor, 
            spendera tid på hobbyer och utveckla gemensamma intressen.
          </p>
        </div>
        
        <div className="grid md:grid-cols-3 gap-6">
          {testimonials.map((testimonial, index) => (
            <Card key={index} className="h-full hover:shadow-soft transition-shadow duration-300">
              <CardContent className="p-6 space-y-4">
                <div className="text-4xl mb-4">{testimonial.image}</div>
                
                <h3 className="font-semibold text-lg text-primary">
                  {testimonial.story}
                </h3>
                
                <p className="text-muted-foreground leading-relaxed">
                  {testimonial.description}
                </p>
                
                <div className="pt-4 border-t">
                  <p className="font-medium text-foreground">
                    {testimonial.name}
                  </p>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  );
};

export default TestimonialsSection;