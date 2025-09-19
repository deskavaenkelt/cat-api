import Header from "@/components/Header";
import Hero from "@/components/Hero";
import EventsSection from "@/components/EventsSection";
import CategoriesSection from "@/components/CategoriesSection";
import CitiesSection from "@/components/CitiesSection";
import HowItWorksSection from "@/components/HowItWorksSection";
import TestimonialsSection from "@/components/TestimonialsSection";
import JoinSection from "@/components/JoinSection";
import Footer from "@/components/Footer";

const Index = () => {
  return (
    <div className="min-h-screen bg-background">
      <Header />
      <main>
        <Hero />
        <EventsSection />
        <CategoriesSection />
        <CitiesSection />
        <HowItWorksSection />
        <TestimonialsSection />
        <JoinSection />
      </main>
      <Footer />
    </div>
  );
};

export default Index;
