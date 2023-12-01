document.addEventListener("DOMContentLoaded", function () {
    function handleErrors(response) {
        if (!response.ok) {
            return response.json().then(errorData => {
                console.error("Error en la respuesta:", response);
                throw Error(`Hubo un problema con la solicitud. Detalles: ${JSON.stringify(errorData)}`);
            });
        }
        return response;
    }

    function logError(error) {
      console.error("Error:", error);
    }

    // Odontólogos
    const odontologoForm = document.getElementById("odontologo-form");
    const listarOdontologosBtn = document.getElementById("listarOdontologos");
    const odontologoList = document.getElementById("odontologo-list");

    odontologoForm.addEventListener("submit", function (event) {
      event.preventDefault();
      const nombreOdontologo = document.getElementById("nombreOdontologo").value;
      const apellidoOdontologo = document.getElementById("apellidoOdontologo").value;
      const matriculaOdontologo = document.getElementById("matriculaOdontologo").value;



      const odontologo = {
        nombre: nombreOdontologo,
        apellido: apellidoOdontologo,
        matricula: matriculaOdontologo,
      };

      fetch("http://localhost:8082/odontologos/registrar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(odontologo),
      })
        .then(handleErrors)
        .then((response) => response.json())
        .then((data) => {
          console.log("Odontólogo registrado:", data);
        })
        .catch(logError);
    });

    listarOdontologosBtn.addEventListener("click", function () {
      fetch("http://localhost:8082/odontologos")
        .then(handleErrors)
        .then((response) => response.json())
        .then((odontologos) => {
          odontologoList.innerHTML = "";

          odontologos.forEach((odontologo) => {
            const listItem = document.createElement("li");
            listItem.textContent = `ID: ${odontologo.id}, Nombre: ${odontologo.nombre}, Apellido: ${odontologo.apellido}, Matrícula: ${odontologo.matricula}`;
            odontologoList.appendChild(listItem);
          });
        })
        .catch(logError);
    });

    // Pacientes
    const pacienteForm = document.getElementById("paciente-form");
    const listarPacientesBtn = document.getElementById("listarPacientes");
    const pacienteList = document.getElementById("paciente-list");

    pacienteForm.addEventListener("submit", function (event) {
      event.preventDefault();
      const nombrePaciente = document.getElementById("nombrePaciente").value;
      const apellidoPaciente = document.getElementById("apellidoPaciente").value;
      const dniPaciente = document.getElementById("dniPaciente").value;

      const pacienteData = {
        nombre: nombrePaciente,
        apellido: apellidoPaciente,
        dni: dniPaciente,
      };

      fetch("http://localhost:8082/pacientes/registrar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(pacienteData),
      })
        .then(handleErrors)
        .then((response) => response.json())
        .then((data) => {
          console.log("Paciente registrado:", data);
        })
        .catch(logError);
    });

    listarPacientesBtn.addEventListener("click", function () {
      fetch("http://localhost:8082/pacientes")
        .then(handleErrors)
        .then((response) => response.json())
        .then((pacientes) => {

          pacienteList.innerHTML = "";


          pacientes.forEach((paciente) => {
            const listItem = document.createElement("li");
            listItem.textContent = `ID: ${paciente.id}, Nombre: ${paciente.nombre}, Apellido: ${paciente.apellido}, DNI: ${paciente.dni}`;
            pacienteList.appendChild(listItem);
          });
        })
        .catch(logError);
    });

    // Turnos
    const turnoForm = document.getElementById("turno-form");
    const listarTurnosBtn = document.getElementById("listarTurnos");
    const turnoList = document.getElementById("turno-list");

    turnoForm.addEventListener("submit", function (event) {
      event.preventDefault();
      const pacienteTurno = document.getElementById("pacienteTurno").value;
      const odontologoTurno = document.getElementById("odontologoTurno").value;
      const fechaTurno = document.getElementById("fechaTurno").value;

      const turnoData = {
        paciente: pacienteTurno,
        odontologo: odontologoTurno,
        fechaYHora: fechaTurno,
      };

      fetch("http://localhost:8082/turnos/registrar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(turnoData),
      })
        .then(handleErrors)
        .then((response) => response.json())
        .then((data) => {
          console.log("Turno registrado:", data);
        })
        .catch(logError);
    });

    listarTurnosBtn.addEventListener("click", function () {
      fetch("http://localhost:8082/turnos")
        .then(handleErrors)
        .then((response) => response.json())
        .then((turnos) => {
          turnoList.innerHTML = "";

          turnos.forEach((turno) => {
            const listItem = document.createElement("li");
            listItem.textContent = `ID: ${turno.id}, Paciente: ${turno.pacienteTurnoSalidaDto.nombre} ${turno.pacienteTurnoSalidaDto.apellido}, Odontólogo: ${turno.odontologoTurnoSalidaDto.nombre} ${turno.odontologoTurnoSalidaDto.apellido}, Fecha y Hora: ${turno.fechaYHora}`;
            turnoList.appendChild(listItem);
          });
        })
        .catch(logError);
    });
  });
  