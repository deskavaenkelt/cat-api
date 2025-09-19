import React from 'react';

const image = (name: string) => `/assets/${name}`;

const CatsPage: React.FC = () => {
  return (
    <div className="p-0 sm:p-0">
      {/* Hero section using inspiration hero image */}
      <section
        className="relative h-56 sm:h-72 md:h-80 w-full overflow-hidden"
        aria-label="Katter hero"
      >
        <img
          src={image('hero-cats.jpg')}
          alt="Lekfulla katter i ett soligt fönster"
          className="absolute inset-0 h-full w-full object-cover"
          onError={(e) => {
            const el = e.currentTarget as HTMLImageElement;
            if (!el.dataset.fallback) {
              el.dataset.fallback = '1';
              el.src = '/assets/placeholder.svg';
            }
          }}
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/60 via-black/20 to-transparent" />
        <div className="relative z-10 mx-auto flex h-full max-w-5xl items-end px-3 sm:px-4 pb-4 sm:pb-6">
          <div>
            <h1 className="text-2xl sm:text-3xl md:text-4xl font-extrabold text-white drop-shadow">
              Katter
            </h1>
            <p className="mt-1 text-white/90 max-w-xl text-sm sm:text-base">
              Upptäck våra katt-historier, evenemang och bilder. Inspirerad av Cat‑tastic Canvas.
            </p>
          </div>
        </div>
      </section>

      {/* Feature cards with images from inspiration */}
      <section className="mx-auto max-w-5xl px-3 sm:px-4 py-5 sm:py-8">
        <div className="grid gap-4 sm:gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3">
          {[
            {
              title: 'Stadskatter',
              img: 'city-cats.jpg',
              desc: 'Urban elegans i varje tass-steg.'
            },
            {
              title: 'Kattcafé',
              img: 'cat-cafe.jpg',
              desc: 'En kopp kaffe och en spinnande vän.'
            },
            {
              title: 'Adoptionsevent',
              img: 'adoption-event.jpg',
              desc: 'Ge ett nytt hem till en lurvig vän.'
            },
            {
              title: 'Kattworkshop',
              img: 'cat-workshop.jpg',
              desc: 'Lär dig mer om kattvård och bus.'
            },
          ].map((card) => (
            <article key={card.title} className="overflow-hidden rounded-lg border border-border bg-card text-card-foreground shadow-soft transition hover:shadow-warm">
              <div className="aspect-[16/9] w-full overflow-hidden bg-muted">
                <img src={image(card.img)} alt={card.title} className="h-full w-full object-cover" onError={(e) => {
                  const el = e.currentTarget as HTMLImageElement;
                  if (!el.dataset.fallback) {
                    el.dataset.fallback = '1';
                    el.src = '/assets/placeholder.svg';
                  }
                }} />
              </div>
              <div className="p-4">
                <h3 className="text-lg font-semibold">{card.title}</h3>
                <p className="mt-1 text-sm text-muted-foreground">{card.desc}</p>
              </div>
            </article>
          ))}
        </div>

        {/* Placeholder notice retained but restyled */}
        <div className="mt-6 rounded border border-dashed border-border bg-card p-4 sm:p-6 text-muted-foreground">
          Mer funktionalitet kommer snart. Detta är en bildrik förhandstitt med tema och inspiration på plats.
        </div>
      </section>
    </div>
  );
};

export default CatsPage;
