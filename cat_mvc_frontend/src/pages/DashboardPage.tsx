import React, { useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import Button from '../components/Button';
import { useNavigate } from 'react-router-dom';

const ProfilePage: React.FC = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [isEditing, setIsEditing] = useState(false);
  const [editData, setEditData] = useState({
    fullName: user?.fullName || '',
    email: user?.email || ''
  });

  const handleSave = () => {
    // TODO: Implement API call to update user profile
    setIsEditing(false);
    console.log('Saving profile:', editData);
  };

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const accountCreated = new Date().toLocaleDateString('sv-SE');

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-4xl mx-auto px-4">
        <div className="bg-white shadow rounded-lg">
          <div className="px-6 py-4 border-b border-gray-200">
            <h1 className="text-2xl font-bold text-gray-900">Min Profil</h1>
            <p className="text-gray-600 mt-1">Hantera dina kontoinställningar och personlig information</p>
          </div>

          <div className="p-6">
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
              {/* Profile Info */}
              <div className="lg:col-span-2">
                <div className="mb-6">
                  <div className="flex items-center justify-between mb-4">
                    <h2 className="text-lg font-semibold text-gray-900">Personlig Information</h2>
                    <Button 
                      variant="secondary"
                      onClick={() => setIsEditing(!isEditing)}
                    >
                      {isEditing ? 'Avbryt' : 'Redigera'}
                    </Button>
                  </div>

                  <div className="space-y-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        Fullständigt namn
                      </label>
                      {isEditing ? (
                        <input
                          type="text"
                          value={editData.fullName}
                          onChange={(e) => setEditData({...editData, fullName: e.target.value})}
                          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
                        />
                      ) : (
                        <p className="text-gray-900 py-2">{user?.fullName || 'Ej angivet'}</p>
                      )}
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        E-postadress
                      </label>
                      {isEditing ? (
                        <input
                          type="email"
                          value={editData.email}
                          onChange={(e) => setEditData({...editData, email: e.target.value})}
                          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
                        />
                      ) : (
                        <p className="text-gray-900 py-2">{user?.email}</p>
                      )}
                    </div>

                    {isEditing && (
                      <div className="pt-4">
                        <Button 
                          onClick={handleSave}
                          variant="primary"
                        >
                          Spara ändringar
                        </Button>
                      </div>
                    )}
                  </div>
                </div>

                <div className="border-t pt-6">
                  <h3 className="text-lg font-semibold text-gray-900 mb-4">Kontoinställningar</h3>
                  <div className="space-y-3">
                    <div className="flex items-center justify-between py-2">
                      <span className="text-gray-700">Ändra lösenord</span>
                      <Button variant="ghost">
                        Kommer snart
                      </Button>
                    </div>
                    <div className="flex items-center justify-between py-2">
                      <span className="text-gray-700">Två-faktor autentisering</span>
                      <Button variant="ghost">
                        Kommer snart
                      </Button>
                    </div>
                  </div>
                </div>
              </div>

              {/* Account Summary */}
              <div className="bg-gray-50 rounded-lg p-6">
                <h3 className="text-lg font-semibold text-gray-900 mb-4">Kontoöversikt</h3>
                <div className="space-y-4">
                  <div>
                    <p className="text-sm font-medium text-gray-500">Kontostatus</p>
                    <p className="text-sm text-green-600 font-semibold">Aktiv</p>
                  </div>
                  <div>
                    <p className="text-sm font-medium text-gray-500">Medlem sedan</p>
                    <p className="text-sm text-gray-900">{accountCreated}</p>
                  </div>
                  <div>
                    <p className="text-sm font-medium text-gray-500">Senaste inloggning</p>
                    <p className="text-sm text-gray-900">Idag</p>
                  </div>
                </div>

                <div className="mt-6 pt-4 border-t border-gray-200">
                  <Button 
                    onClick={() => navigate('/cat-management')}
                    variant="primary"
                    className="w-full mb-3"
                  >
                    Hantera Katter
                  </Button>
                  <Button 
                    onClick={handleLogout}
                    variant="secondary"
                    className="w-full"
                  >
                    Logga ut
                  </Button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;