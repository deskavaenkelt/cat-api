import { Button } from "@/components/ui/button";

const Footer = () => {
  return (
    <footer className="bg-foreground text-background py-12">
      <div className="container mx-auto px-4">
        <div className="grid md:grid-cols-4 gap-8 mb-8">
          <div>
            <h3 className="font-bold text-lg mb-4">Ditt konto</h3>
            <ul className="space-y-2 text-sm">
              <li><a href="#" className="hover:text-primary transition-colors">Registrera dig</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Logga in</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Hjälp</a></li>
            </ul>
          </div>
          
          <div>
            <h3 className="font-bold text-lg mb-4">Upptäck</h3>
            <ul className="space-y-2 text-sm">
              <li><a href="#" className="hover:text-primary transition-colors">Grupper</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Kalender</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Ämnen</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Städer</a></li>
            </ul>
          </div>
          
          <div>
            <h3 className="font-bold text-lg mb-4">KattKlub</h3>
            <ul className="space-y-2 text-sm">
              <li><a href="#" className="hover:text-primary transition-colors">Om oss</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Blogg</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">KattKlub Pro</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Karriär</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Appar</a></li>
              <li><a href="#" className="hover:text-primary transition-colors">Podcast</a></li>
            </ul>
          </div>
          
          <div>
            <h3 className="font-bold text-lg mb-4">Skapa din egen KattKlub-grupp</h3>
            <Button variant="secondary" className="w-full">
              Kom igång
            </Button>
          </div>
        </div>
        
        <div className="border-t border-background/20 pt-8">
          <div className="flex flex-col md:flex-row justify-between items-center gap-4">
            <div className="flex items-center gap-4">
              <span className="text-2xl font-bold">🐱 KattKlub</span>
              <span className="text-sm text-background/70">© 2024 KattKlub</span>
            </div>
            
            <div className="flex items-center gap-4 text-sm">
              <a href="#" className="hover:text-primary transition-colors">Villkor</a>
              <a href="#" className="hover:text-primary transition-colors">Integritet</a>
              <a href="#" className="hover:text-primary transition-colors">Cookies</a>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;