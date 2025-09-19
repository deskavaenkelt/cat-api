const API_BASE = '';

export interface Cat {
  id?: number;
  name: string;
  breed: string;
}

const getAuthHeader = (): Record<string, string> => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const catService = {
  async getAllCats(): Promise<Cat[]> {
    const response = await fetch(`${API_BASE}/cats`, {
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
    });
    
    if (!response.ok) {
      throw new Error('Failed to fetch cats');
    }
    
    return response.json();
  },

  async getCatById(id: number): Promise<Cat> {
    const response = await fetch(`${API_BASE}/cats/${id}`, {
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
    });
    
    if (!response.ok) {
      throw new Error('Failed to fetch cat');
    }
    
    return response.json();
  },

  async createCat(cat: Omit<Cat, 'id'>): Promise<Cat> {
    const response = await fetch(`${API_BASE}/cats`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
      body: JSON.stringify(cat),
    });
    
    if (!response.ok) {
      throw new Error('Failed to create cat');
    }
    
    return response.json();
  },

  async updateCat(id: number, cat: Cat): Promise<Cat> {
    const response = await fetch(`${API_BASE}/cats/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
      body: JSON.stringify(cat),
    });
    
    if (!response.ok) {
      throw new Error('Failed to update cat');
    }
    
    return response.json();
  },

  async deleteCat(id: number): Promise<void> {
    const response = await fetch(`${API_BASE}/cats/${id}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
    });
    
    if (!response.ok) {
      throw new Error('Failed to delete cat');
    }
  },

  async addPredefinedCats(): Promise<void> {
    const response = await fetch(`${API_BASE}/cats/addPredefinedCats`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
      },
    });
    
    if (!response.ok) {
      throw new Error('Failed to add predefined cats');
    }
  },
};