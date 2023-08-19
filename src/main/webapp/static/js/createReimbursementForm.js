document.addEventListener('DOMContentLoaded', function() {
    const createBtn = document.getElementById('createBtn');
    createBtn.addEventListener('click', createReimbursementForm);
});
function createReimbursementForm() {
    const formContainer = document.getElementById('form-container');

    formContainer.innerHTML = '';

    const form = document.createElement('form');
    form.id = 'reimbursement-form';

    const dateRangeLabel = document.createElement('label');
    dateRangeLabel.textContent = 'Select Date Range:';
    const dateRangeStartInput = document.createElement('input');
    dateRangeStartInput.type = 'date';
    dateRangeStartInput.name = 'dateRangeStart';
    dateRangeStartInput.required = true;

    const dateRangeEndInput = document.createElement('input');
    dateRangeEndInput.type = 'date';
    dateRangeEndInput.name = 'dateRangeEnd';
    dateRangeEndInput.required = true;

    const carUsageCheckbox = document.createElement('input');
    carUsageCheckbox.type = 'checkbox';
    carUsageCheckbox.name = 'carUsage';
    carUsageCheckbox.id = 'carUsage';
    const carUsageLabel = document.createElement('label');
    carUsageLabel.textContent = 'Car usage';
    carUsageLabel.setAttribute('for', 'carUsage');

    const carDistanceLabel = document.createElement('label');
    carDistanceLabel.textContent = 'Claim for Car distance:';
    const distanceInput = document.createElement('input');
    distanceInput.type = 'number';
    distanceInput.name = 'distance';
    distanceInput.placeholder = 'Driven distance';
    distanceInput.disabled = true;

    carUsageCheckbox.addEventListener('change', function () {
        distanceInput.disabled = !this.checked;
    });

    const selectedDaysContainer = document.createElement('div');
    selectedDaysContainer.id = 'selected-days-container';

    const receiptsLabel = document.createElement('label');
    receiptsLabel.textContent = 'Receipts:';
    const receiptsSelect = document.createElement('select');
    receiptsSelect.name = 'receipts';
    const receiptOptions = ['Taxi', 'Hotel', 'Plane Ticket', 'Train'];
    for (const optionText of receiptOptions) {
        const option = document.createElement('option');
        option.value = optionText;
        option.textContent = optionText;
        receiptsSelect.appendChild(option);
    }

    const addReceiptButton = document.createElement('button');
    addReceiptButton.type = 'button';
    addReceiptButton.textContent = 'Add New Receipt';
    addReceiptButton.addEventListener('click', addNewReceipt);

    const receiptContainer = document.createElement('div');
    receiptContainer.id = 'receipt-container';

    const submitButton = document.createElement('button');
    submitButton.type = 'button';
    submitButton.textContent = 'Calculate Reimbursement';
    submitButton.onclick = calculateReimbursement;

    const totalReimbursementLabel = document.createElement('p');
    totalReimbursementLabel.id = 'total-reimbursement';
    totalReimbursementLabel.textContent = 'Total Reimbursement: $0.00';

    const selectedDaysContainerWrapper = document.createElement('div');
    selectedDaysContainerWrapper.appendChild(selectedDaysContainer);

    form.appendChild(dateRangeLabel);
    form.appendChild(dateRangeStartInput);
    form.appendChild(dateRangeEndInput);
    form.appendChild(document.createElement('br'));
    form.appendChild(selectedDaysContainerWrapper);
    form.appendChild(document.createElement('br'));
    form.appendChild(carUsageLabel);
    form.appendChild(carUsageCheckbox);
    form.appendChild(document.createElement('br'));
    form.appendChild(carDistanceLabel);
    form.appendChild(distanceInput);
    form.appendChild(document.createElement('br'));
    form.appendChild(receiptsLabel);
    form.appendChild(receiptsSelect);
    form.appendChild(addReceiptButton);
    form.appendChild(receiptContainer);
    form.appendChild(document.createElement('br'));
    form.appendChild(submitButton);
    form.appendChild(document.createElement('br'));
    form.appendChild(totalReimbursementLabel);

    formContainer.appendChild(form);

    let receiptIndex = 0;

    function addNewReceipt() {
        const selectedReceipt = document.querySelector('select[name="receipts"]').value;
        const receiptAmount = prompt(`Enter the amount for ${selectedReceipt}:`);

        if (receiptAmount !== null && receiptAmount.trim() !== '') {
            const receiptContainer = document.getElementById('receipt-container');
            const receiptEntry = document.createElement('div');
            receiptEntry.textContent = `${selectedReceipt}: $${parseFloat(receiptAmount).toFixed(2)}`;
            receiptContainer.appendChild(receiptEntry);

            const hiddenNameInput = document.createElement('input');
            hiddenNameInput.type = 'hidden';
            hiddenNameInput.name = `receipts[${receiptIndex}].name`;
            hiddenNameInput.value = selectedReceipt;
            receiptContainer.appendChild(hiddenNameInput);

            const hiddenPriceInput = document.createElement('input');
            hiddenPriceInput.type = 'hidden';
            hiddenPriceInput.name = `receipts[${receiptIndex}].price`;
            hiddenPriceInput.value = parseFloat(receiptAmount).toFixed(2);
            receiptContainer.appendChild(hiddenPriceInput);

            receiptIndex++;
        }
    }

    function updateSelectedDays() {
        selectedDaysContainerWrapper.innerHTML = '';
        const startDate = new Date(dateRangeStartInput.value);
        const endDate = new Date(dateRangeEndInput.value);
        let currentDate = new Date(startDate);

        while (currentDate <= endDate) {
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.name = 'selectedDays';
            checkbox.value = currentDate.toISOString().split('T')[0];
            const label = document.createElement('label');
            label.textContent = currentDate.toDateString();
            selectedDaysContainerWrapper.appendChild(checkbox);
            selectedDaysContainerWrapper.appendChild(label);

            currentDate.setDate(currentDate.getDate() + 1);
        }
    }

    dateRangeStartInput.addEventListener('change', updateSelectedDays);
    dateRangeEndInput.addEventListener('change', updateSelectedDays);
    updateSelectedDays();
}

document.addEventListener('DOMContentLoaded', function() {
    const calculateBtn = document.querySelector('button[type="button"][value="Calculate Reimbursement"]');
    calculateBtn.addEventListener('click', calculateReimbursement);

    const carUsageCheckbox = document.getElementById('carUsage');
    const distanceInput = document.querySelector('input[name="distance"]');
    carUsageCheckbox.addEventListener('change', function () {
        distanceInput.disabled = !this.checked;
    });

    const addReceiptButton = document.querySelector('button[type="button"][value="Add New Receipt"]');
    addReceiptButton.addEventListener('click', addNewReceipt);
});

function calculateReimbursement() {
    const totalReimbursementField = document.getElementById('total-reimbursement');
    const formData = new FormData(document.getElementById('reimbursement-form'));
    const formDataJson = {};

    formDataJson.selectedDays = Array.from(formData.getAll('selectedDays'));
    formDataJson.carUsage = formData.get('carUsage') === 'on';
    formDataJson.distance = parseFloat(formData.get('distance')) || 0;

    const receipts = [];

    let receiptIndex = 0;
    while (true) {
        const receiptName = formData.get(`receipts[${receiptIndex}].name`);
        const receiptPrice = parseFloat(formData.get(`receipts[${receiptIndex}].price`));

        if (receiptName === null || isNaN(receiptPrice)) {
            break;
        }

        receipts.push({ name: receiptName, price: receiptPrice });
        receiptIndex++;
    }

    formDataJson.receipts = receipts;

    console.log(formDataJson);

    fetch('http://localhost:8080/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formDataJson)
    })
        .then(response => response.json())
        .then(data => {
            totalReimbursementField.textContent = `Total Reimbursement: $${data.totalCost.toFixed(2)}`;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
