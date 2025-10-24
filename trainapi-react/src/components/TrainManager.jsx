import { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const TrainManager = () => {
  const [trains, setTrains] = useState([]);
  const [train, setTrain] = useState({
    id: '',
    name: '',
    type: '',
    source: '',
    destination: '',
    totalCoaches: '',
    capacity: '',
    fare: '',
    contactEmail: '',
    contactNumber: '',
    schedule: '',
    status: '',
    rating: '',
    description: '',
    dateOfIntroduction: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedTrain, setFetchedTrain] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  // ✅ Use config.js URL + /trainapi
  const baseUrl = `${config.url}/trainapi`;

  useEffect(() => {
    fetchAllTrains();
  }, []);

  const fetchAllTrains = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setTrains(res.data);
    } catch {
      setMessage('Failed to fetch trains.');
    }
  };

  const handleChange = (e) => {
    const value = ['totalCoaches', 'capacity', 'fare', 'rating'].includes(e.target.name)
      ? parseFloat(e.target.value)
      : e.target.value;
    setTrain({ ...train, [e.target.name]: value });
  };

  const validateForm = () => {
    for (let key in train) {
      if (!train[key] || train[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addTrain = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, train);
      setMessage('Train added successfully.');
      fetchAllTrains();
      resetForm();
    } catch {
      setMessage('Error adding train.');
    }
  };

  const updateTrain = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, train);
      setMessage('Train updated successfully.');
      fetchAllTrains();
      resetForm();
    } catch {
      setMessage('Error updating train.');
    }
  };

  const deleteTrain = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllTrains();
    } catch {
      setMessage('Error deleting train.');
    }
  };

  const getTrainById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedTrain(res.data);
      setMessage('');
    } catch {
      setFetchedTrain(null);
      setMessage('Train not found.');
    }
  };

  const handleEdit = (t) => {
    setTrain(t);
    setEditMode(true);
    setMessage(`Editing train with ID ${t.id}`);
  };

  const resetForm = () => {
    setTrain({
      id: '',
      name: '',
      type: '',
      source: '',
      destination: '',
      totalCoaches: '',
      capacity: '',
      fare: '',
      contactEmail: '',
      contactNumber: '',
      schedule: '',
      status: '',
      rating: '',
      description: '',
      dateOfIntroduction: ''
    });
    setEditMode(false);
  };

  return (
    <div className="train-container">
      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Train Management</h2>

      {/* Add / Edit Form */}
      <div>
        <h3>{editMode ? 'Edit Train' : 'Add Train'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={train.id} onChange={handleChange} />
          <input type="text" name="name" placeholder="Train Name" value={train.name} onChange={handleChange} />
          <select name="type" value={train.type} onChange={handleChange}>
            <option value="">Select Train Type</option>
            <option value="Express">Express</option>
            <option value="Passenger">Passenger</option>
            <option value="Superfast">Superfast</option>
            <option value="Mail">Mail</option>
          </select>
          <input type="text" name="source" placeholder="Source Station" value={train.source} onChange={handleChange} />
          <select name="destination" value={train.destination} onChange={handleChange}>
            <option value="">Select Destination</option>
            <option value="New Delhi">New Delhi</option>
            <option value="Mumbai">Mumbai</option>
            <option value="Chennai">Chennai</option>
            <option value="Kolkata">Kolkata</option>
            <option value="Bangalore">Bangalore</option>
            <option value="Hyderabad">Hyderabad</option>
          </select>
          <select name="totalCoaches" value={train.totalCoaches} onChange={handleChange}>
            <option value="">Select Total Coaches</option>
            <option value="6">6</option>
            <option value="8">8</option>
            <option value="10">10</option>
            <option value="12">12</option>
            <option value="15">15</option>
          </select>
          <select name="capacity" value={train.capacity} onChange={handleChange}>
            <option value="">Select Capacity</option>
            <option value="200">200</option>
            <option value="400">400</option>
            <option value="600">600</option>
            <option value="800">800</option>
            <option value="1000">1000</option>
          </select>
          <input type="number" name="fare" placeholder="Fare" value={train.fare} onChange={handleChange} />
          <input type="email" name="contactEmail" placeholder="Contact Email" value={train.contactEmail} onChange={handleChange} />
          <input type="text" name="contactNumber" placeholder="Contact Number" value={train.contactNumber} onChange={handleChange} />
          <select name="schedule" value={train.schedule} onChange={handleChange}>
            <option value="">Select Schedule</option>
            <option value="Daily">Daily</option>
            <option value="Weekly">Weekly</option>
            <option value="Biweekly">Biweekly</option>
          </select>
          <select name="status" value={train.status} onChange={handleChange}>
            <option value="">Select Status</option>
            <option value="Running">Running</option>
            <option value="Delayed">Delayed</option>
            <option value="Cancelled">Cancelled</option>
          </select>
          <input type="number" name="rating" placeholder="Rating" value={train.rating} onChange={handleChange} />
          <input type="text" name="description" placeholder="Description" value={train.description} onChange={handleChange} />
          <input type="date" name="dateOfIntroduction" value={train.dateOfIntroduction} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addTrain}>Add Train</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateTrain}>Update Train</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      {/* Fetch Train By ID */}
      <div>
        <h3>Get Train By ID</h3>
        <input type="number" value={idToFetch} onChange={(e) => setIdToFetch(e.target.value)} placeholder="Enter ID" />
        <button className="btn-blue" onClick={getTrainById}>Fetch</button>

        {fetchedTrain && (
          <div>
            <h4>Train Found:</h4>
            <pre>{JSON.stringify(fetchedTrain, null, 2)}</pre>
          </div>
        )}
      </div>

      {/* All Trains */}
      <div>
        <h3>All Trains</h3>
        {trains.length === 0 ? (
          <p>No trains found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(train).map((key) => <th key={key}>{key}</th>)}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {trains.map((t) => (
                  <tr key={t.id}>
                    {Object.keys(train).map((key) => <td key={key}>{t[key]}</td>)}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(t)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteTrain(t.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default TrainManager;
