import { useEffect, useState } from 'react';
import { CatsApi, Cat } from '../api/cats';

export function useCats() {
  const [data, setData] = useState<Cat[] | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    let isMounted = true;
    setLoading(true);
    CatsApi.list()
      .then((cats) => {
        if (isMounted) setData(cats);
      })
      .catch((e) => {
        if (isMounted) setError(e as Error);
      })
      .finally(() => {
        if (isMounted) setLoading(false);
      });
    return () => {
      isMounted = false;
    };
  }, []);

  return { data, loading, error };
}
