-- Table for main incidence records
CREATE TABLE IF NOT EXISTS devolution (
    devolution_id INT AUTO_INCREMENT PRIMARY KEY,
    shipment_id INT NOT NULL,            -- Foreign Key reference to shipment
    store_id INT NOT NULL,               -- Foreign Key reference to store
    user_email VARCHAR(255) NOT NULL,    -- Email of the user reporting the incidence
    status ENUM('OPEN', 'ACCEPTED', 'REJECTED') NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL    -- Soft delete field
    );

-- Table for incidence details
CREATE TABLE IF NOT EXISTS devolution_detail (
    devolution_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    devolution_id INT NOT NULL,           -- Foreign Key reference to devolution
    product_sku VARCHAR(255) NOT NULL,   -- SKU of the product affected
    affected_quantity INT NOT NULL,      -- Quantity affected
    reason VARCHAR(255) NOT NULL,        -- Reason for the incidence ('Wrong product', etc.)
    is_damaged BOOLEAN NOT NULL DEFAULT FALSE, -- Whether the product is damaged
    unit_cost DECIMAL(10, 2) NULL,    -- Cost of the product
    total_cost DECIMAL(10, 2) NULL,   -- Total cost of the affected products
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL,   -- Soft delete field
    FOREIGN KEY (devolution_id) REFERENCES devolution(devolution_id) ON DELETE CASCADE
    );
