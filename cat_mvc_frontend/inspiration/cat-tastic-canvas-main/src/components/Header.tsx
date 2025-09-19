import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Search, Menu } from "lucide-react";

const Header = () => {
  return (
    <header className="sticky top-0 z-50 bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 border-b border-border">
      <div className="container mx-auto px-4 h-16 flex items-center justify-between">
        <div className="flex items-center gap-4">
          <h1 className="text-2xl font-bold text-primary">🐱 KattKlub</h1>
        </div>
        
        <div className="hidden md:flex items-center gap-4 flex-1 max-w-md mx-8">
          <div className="relative flex-1">
            <Search className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
            <Input 
              placeholder="Sök kattevenemang..." 
              className="pl-10"
            />
          </div>
        </div>
        
        <div className="flex items-center gap-4">
          <nav className="hidden md:flex items-center gap-6">
            <a href="#events" className="text-sm font-medium text-foreground hover:text-primary transition-colors">
              Evenemang
            </a>
            <a href="#communities" className="text-sm font-medium text-foreground hover:text-primary transition-colors">
              Grupper
            </a>
            <a href="#adoption" className="text-sm font-medium text-foreground hover:text-primary transition-colors">
              Adoption
            </a>
          </nav>
          
          <div className="flex items-center gap-2">
            <Button variant="outline" size="sm">
              Logga in
            </Button>
            <Button variant="hero" size="sm">
              Gå med
            </Button>
          </div>
          
          <Button variant="ghost" size="icon" className="md:hidden">
            <Menu className="h-4 w-4" />
          </Button>
        </div>
      </div>
    </header>
  );
};

export default Header;