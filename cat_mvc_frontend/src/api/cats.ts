import { apiFetch } from './client';

export type Cat = {
  id: string;
  name: string;
  age?: number;
};

export const CatsApi = {
  list: () => apiFetch<Cat[]>('/cats'),
  get: (id: string) => apiFetch<Cat>(`/cats/${id}`),
  create: (cat: Omit<Cat, 'id'>) => apiFetch<Cat>('/cats', { method: 'POST', body: JSON.stringify(cat) }),
  update: (id: string, cat: Partial<Omit<Cat, 'id'>>) => apiFetch<Cat>(`/cats/${id}`, { method: 'PUT', body: JSON.stringify(cat) }),
  remove: (id: string) => apiFetch<void>(`/cats/${id}`, { method: 'DELETE' }),
};
