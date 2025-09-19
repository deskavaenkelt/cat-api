import { Button } from "@/components/ui/button";
import heroCats from "@/assets/hero-cats.jpg";

const Hero = () => {
  return (
    <section className="bg-gradient-soft py-16 md:py-24">
      <div className="container mx-auto px-4">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          <div className="space-y-6">
            <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold text-foreground leading-tight">
              Kattälskare-plattformen där 
              <span className="text-primary block">intressen blir vänskap</span>
            </h1>
            
            <p className="text-lg text-muted-foreground leading-relaxed max-w-2xl">
              Oavsett om du är ny kattägare eller erfaren kattmänniska - här kan du träffa likasinnade, 
              lära dig nya saker och delta i kattrelaterade aktiviteter som händer varje dag.
            </p>
            
            <div className="flex flex-col sm:flex-row gap-4">
              <Button variant="hero" size="lg">
                Gå med i KattKlub
              </Button>
              <Button variant="outline" size="lg">
                Hitta evenemang
              </Button>
            </div>
          </div>
          
          <div className="relative">
            <img 
              src={heroCats} 
              alt="Lyckliga katter som leker tillsammans" 
              className="w-full h-auto rounded-lg shadow-warm"
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default Hero;