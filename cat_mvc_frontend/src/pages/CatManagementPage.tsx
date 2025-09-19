import React, { useState, useEffect } from 'react';
import { catService, Cat } from '../services/catService';
import Button from '../components/Button';
import { useAuth } from '../contexts/AuthContext';

const CatManagementPage: React.FC = () => {
  const { isAdmin } = useAuth();
  const [cats, setCats] = useState<Cat[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [editingCat, setEditingCat] = useState<Cat | null>(null);
  const [formData, setFormData] = useState({ name: '', breed: '' });

  useEffect(() => {
    loadCats();
  }, []);

  const loadCats = async () => {
    try {
      setLoading(true);
      const catsData = await catService.getAllCats();
      setCats(catsData);
    } catch (err) {
      setError('Kunde inte ladda katter');
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editingCat) {
        await catService.updateCat(editingCat.id!, { ...formData, id: editingCat.id });
      } else {
        await catService.createCat(formData);
      }
      await loadCats();
      resetForm();
    } catch (err) {
      setError(editingCat ? 'Kunde inte uppdatera katt' : 'Kunde inte skapa katt');
    }
  };

  const handleEdit = (cat: Cat) => {
    setEditingCat(cat);
    setFormData({ name: cat.name, breed: cat.breed });
    setShowForm(true);
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Är du säker på att du vill ta bort denna katt?')) {
      try {
        await catService.deleteCat(id);
        await loadCats();
      } catch (err) {
        setError('Kunde inte ta bort katt');
      }
    }
  };

  const handleAddPredefined = async () => {
    try {
      await catService.addPredefinedCats();
      await loadCats();
    } catch (err) {
      setError('Kunde inte lägga till fördefinierade katter');
    }
  };

  const resetForm = () => {
    setShowForm(false);
    setEditingCat(null);
    setFormData({ name: '', breed: '' });
    setError('');
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-lg">Laddar katter...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-6xl mx-auto px-4">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Kattadministration</h1>
          <div className="flex gap-2">
            {isAdmin && (
              <Button 
                onClick={handleAddPredefined}
                variant="secondary"
              >
                Lägg till exempel-katter
              </Button>
            )}
            <Button 
              onClick={() => setShowForm(true)}
              variant="primary"
            >
              Lägg till katt
            </Button>
          </div>
        </div>

        {error && (
          <div className="mb-4 bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded">
            {error}
          </div>
        )}

        {showForm && (
          <div className="bg-white rounded-lg shadow p-6 mb-8">
            <h2 className="text-xl font-semibold mb-4">
              {editingCat ? 'Redigera katt' : 'Lägg till ny katt'}
            </h2>
            <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Namn
                </label>
                <input
                  type="text"
                  value={formData.name}
                  onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
                  required
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Ras
                </label>
                <input
                  type="text"
                  value={formData.breed}
                  onChange={(e) => setFormData({ ...formData, breed: e.target.value })}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
                  required
                />
              </div>
              <div className="md:col-span-2 flex gap-2">
                <Button type="submit" variant="primary">
                  {editingCat ? 'Uppdatera' : 'Skapa'}
                </Button>
                <Button type="button" onClick={resetForm} variant="secondary">
                  Avbryt
                </Button>
              </div>
            </form>
          </div>
        )}

        <div className="bg-white rounded-lg shadow overflow-hidden">
          <div className="px-6 py-4 border-b border-gray-200">
            <h2 className="text-lg font-semibold text-gray-900">
              Alla katter ({cats.length})
            </h2>
          </div>
          
          {cats.length === 0 ? (
            <div className="p-8 text-center text-gray-500">
              <p className="text-lg mb-2">Inga katter registrerade än</p>
              <p className="text-sm">Lägg till din första katt eller ladda exempel-katter</p>
            </div>
          ) : (
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                  <tr>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      ID
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Namn
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Ras
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Åtgärder
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {cats.map((cat) => (
                    <tr key={cat.id}>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        {cat.id}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                        {cat.name}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        {cat.breed}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm font-medium space-x-2">
                        <button
                          onClick={() => handleEdit(cat)}
                          className="text-orange-600 hover:text-orange-900"
                        >
                          Redigera
                        </button>
                        <button
                          onClick={() => handleDelete(cat.id!)}
                          className="text-red-600 hover:text-red-900"
                        >
                          Ta bort
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default CatManagementPage;