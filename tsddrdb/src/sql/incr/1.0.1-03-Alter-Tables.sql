UPDATE tsddr_t_report SET xml_report = '<?xml version="1.0" encoding="UTF-8"?>
<!-- Created with Jaspersoft Studio version 6.20.0.final utlizzo versione della libreria JasperReports6.20.0-2bc7ab61c56f459e8176eb05c7705e145cd400ad  -->
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="UMA_MODELLO_26_V" pageWidth="595" pageHeight="900" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" whenResourceMissingType="Error" uuid="3e2d4fe0-be96-423a-93be-f74048f83696">
	<property name="com.jaspersoft.studio.data.defaultdataadapter" value="Nuovo Data Adapter (2)"/>
	<subDataset name="soggettimrDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="codFiscPartiva" class="java.lang.String"/>
		<field name="ragSociale" class="java.lang.String"/>
		<field name="obbRagg" class="java.lang.String"/>
		<field name="idSoggettiMr" class="java.lang.Long"/>
	</subDataset>
	<subDataset name="versamentiDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="dataVersamento" class="java.util.Date"/>
		<field name="importoVersato" class="java.lang.Double"/>
	</subDataset>
	<subDataset name="provvedimentiDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="dataProvvedimento" class="java.util.Date"/>
		<field name="numProvvedimento" class="java.lang.String"/>
		<field name="tipoProvvedimento.descTipoProvvedimento" class="java.lang.String"/>
		<field name="dataInizioAutorizzazione" class="java.util.Date"/>
		<field name="dataFineAutorizzazione" class="java.util.Date"/>
	</subDataset>
	<subDataset name="rifiutoConferitoDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="quantita" class="java.lang.Double"/>
		<field name="importo" class="java.lang.Double"/>
	</subDataset>
	<subDataset name="totalerifiutiConferitiDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="totale.quantita" class="java.lang.Double"/>
		<field name="totale.importo" class="java.lang.Double"/>
	</subDataset>
	<subDataset name="rifiutiConferitiDataset" uuid="4d37fa87-5587-4ab4-9454-80c417108cf4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="conferimentiReport" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
		<field name="rifiutoTariffa.descrizione" class="java.lang.String"/>
		<field name="rifiutoTariffa.importo" class="java.lang.Double"/>
		<field name="unitaMisura.descUnitaMisura" class="java.lang.String"/>
		<field name="rifiutoTariffa.flagRiduzione" class="java.lang.Boolean"/>
		<field name="rifiutoTariffa.stringRiduzione" class="java.lang.String"/>
		<field name="rifiutoTariffa.descrizioneCompleta" class="java.lang.String"/>
		<field name="totale.quantita" class="java.lang.Double"/>
		<field name="totale.importo" class="java.lang.Double"/>
	</subDataset>
	<parameter name="LOGO_RP" class="java.awt.image.BufferedImage">
		<parameterDescription><![CDATA[]]></parameterDescription>
	</parameter>
	<parameter name="H_S_1" class="java.lang.String"/>
	<parameter name="H_S_2" class="java.lang.String"/>
	<parameter name="H_S_3" class="java.lang.String"/>
	<parameter name="H_S_4" class="java.lang.String"/>
	<parameter name="H_C_1" class="java.lang.String"/>
	<parameter name="H_C_2" class="java.lang.String"/>
	<parameter name="H_C_3" class="java.lang.String"/>
	<parameter name="TITOLO" class="java.lang.String"/>
	<parameter name="H_D_1" class="java.lang.String"/>
	<parameter name="H_D_2" class="java.lang.String"/>
	<parameter name="ANNO" class="java.lang.Long"/>
	<parameter name="CF_GESTORE" class="java.lang.String"/>
	<parameter name="RAGSOG_GESTORE" class="java.lang.String"/>
	<parameter name="NATGIU_GESTORE" class="java.lang.String"/>
	<parameter name="SEDIME_GESTORE" class="java.lang.String"/>
	<parameter name="IND_GESTORE" class="java.lang.String"/>
	<parameter name="COM_GESTORE" class="java.lang.String"/>
	<parameter name="CAP_GESTORE" class="java.lang.String"/>
	<parameter name="PROV_GESTORE" class="java.lang.String"/>
	<parameter name="TEL_GESTORE" class="java.lang.String"/>
	<parameter name="MAIL_GESTORE" class="java.lang.String"/>
	<parameter name="PEC_GESTORE" class="java.lang.String"/>
	<parameter name="SEZ_L_R" class="java.lang.String"/>
	<parameter name="CF" class="java.lang.String"/>
	<parameter name="NOME" class="java.lang.String"/>
	<parameter name="COGNOME" class="java.lang.String"/>
	<parameter name="QUALIFICA" class="java.lang.String"/>
	<parameter name="totalerifiutiConferiti" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<parameter name="rifiutiConferiti" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<parameter name="rifiutiConferiti.conferimenti" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<parameter name="provvedimenti" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<parameter name="versamenti" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<parameter name="SEZ_DICH" class="java.lang.String"/>
	<parameter name="SEZ_CONF" class="java.lang.String"/>
	<parameter name="totaleQuantita" class="java.lang.Double"/>
	<parameter name="totaleImporto" class="java.lang.Double"/>
	<parameter name="SEZ_IMP" class="java.lang.String"/>
	<parameter name="NOME_IMPIANTO" class="java.lang.String"/>
	<parameter name="TIPO_IMPIANTO" class="java.lang.String"/>
	<parameter name="STATO_IMPIANTO" class="java.lang.String"/>
	<parameter name="SEDIME_IMPIANTO" class="java.lang.String"/>
	<parameter name="INDIRIZZO_IMPIANTO" class="java.lang.String"/>
	<parameter name="COMUNE_IMPIANTO" class="java.lang.String"/>
	<parameter name="CAP_IMPIANTO" class="java.lang.String"/>
	<parameter name="PROV_IMPIANTO" class="java.lang.String"/>
	<parameter name="SEZ_P_A" class="java.lang.String"/>
	<parameter name="SEZ_L_DOC" class="java.lang.String"/>
	<parameter name="SEDIME_LUOGO" class="java.lang.String"/>
	<parameter name="INDIRIZZO_LUOGO" class="java.lang.String"/>
	<parameter name="COMUNE_LUOGO" class="java.lang.String"/>
	<parameter name="CAP_LUOGO" class="java.lang.String"/>
	<parameter name="PROV_LUOGO" class="java.lang.String"/>
	<parameter name="NUMERO_PROVVEDIMENTO" class="java.lang.String"/>
	<parameter name="DATA_PROVVEDIMENTO" class="java.lang.String"/>
	<parameter name="TIPO_PROVVEDIMENTO" class="java.lang.String"/>
	<parameter name="DATA_DECORRENZA_PROVVEDIMENTO" class="java.lang.String"/>
	<parameter name="DATA_SCADENZA_PROVVEDIMENTO" class="java.lang.String"/>
	<parameter name="versamentiCredito" class="java.lang.Double"/>
	<parameter name="versamentiCreditoAP" class="java.lang.Double"/>
	<parameter name="versamentiDebito" class="java.lang.Double"/>
	<parameter name="versamentiTotale" class="java.lang.Double"/>
	<parameter name="versamentiRifiuti" class="java.lang.Double"/>
	<parameter name="SEZ_VERS" class="java.lang.String"/>
	<parameter name="FIRMA" class="java.awt.image.BufferedImage">
		<parameterDescription><![CDATA[]]></parameterDescription>
	</parameter>
	<parameter name="SEZ_PIE_1" class="java.lang.String"/>
	<parameter name="INFO_T" class="java.lang.String"/>
	<parameter name="INFO_D" class="java.lang.String"/>
	<parameter name="SEZ_ANN" class="java.lang.String"/>
	<parameter name="dataDichiarazione" class="java.util.Date"/>
	<parameter name="SEZ_VERS_MR" class="java.lang.String"/>
	<parameter name="SEZ_PIE" class="java.lang.String"/>
	<parameter name="annotazioni" class="java.lang.String"/>
	<parameter name="soggettimr" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<queryString language="JSON">
		<![CDATA[modello26]]>
	</queryString>
	<title>
		<band height="860" splitType="Stretch">
			<property name="com.jaspersoft.studio.layout" value="com.jaspersoft.studio.editor.layout.FreeLayout"/>
			<textField>
				<reportElement x="20" y="35" width="140" height="12" uuid="31c7ee68-36d2-4d5d-8709-8d06620e95cd">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_1}]]></textFieldExpression>
			</textField>
			<image onErrorType="Blank">
				<reportElement x="50" y="0" width="90" height="35" uuid="47de5b3f-19b4-41fb-b2c4-3d2bc39fb0dd"/>
				<imageExpression><![CDATA[$P{LOGO_RP}]]></imageExpression>
			</image>
			<staticText>
				<reportElement mode="Opaque" x="0" y="80" width="555" height="3" backcolor="#374D4F" uuid="22581b8b-75e9-4ab1-9eaa-1409f1a753aa">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.width" value="pixel"/>
				</reportElement>
				<box padding="1"/>
				<textElement textAlignment="Center">
					<font size="14" isBold="false"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<textField>
				<reportElement x="20" y="44" width="140" height="11" uuid="c598dd7f-1181-4f7e-ba85-f837a85b9d95">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_2}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="20" y="53" width="140" height="11" uuid="ac84e615-e24e-4c17-aeaf-05e3ec297088">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_3}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="20" y="62" width="140" height="9" uuid="0e51fd7d-f78f-470a-a29c-a1ef14da974f">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_4}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="170" y="0" width="210" height="34" uuid="b417a835-96fb-4f27-9438-594ff717a10d">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="20" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TITOLO}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="170" y="34" width="210" height="19" uuid="b07ca9f8-9b3c-49d3-8014-d6a3a045be4f">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_1}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="187" y="53" width="180" height="11" uuid="d9756cc6-9881-461b-9ba8-90f4f2c3e5e1">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isItalic="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_2}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="187" y="62" width="180" height="9" uuid="2f6fa418-fe0c-49b7-b8f7-5a2f28d9a691">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_3}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="10" width="140" height="12" uuid="b14c77a2-7b54-455e-a543-cdf017bdc408">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_D_1}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="22" width="140" height="29" uuid="4dc5acc6-4d20-4c97-9e64-a578bf64c716">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="18" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{ANNO}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="51" width="140" height="20" uuid="2ed34758-e20d-4f8b-9796-573293e17d1b">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_D_2}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="5" y="83" width="544" height="15" uuid="abbee761-b513-4dc6-acc3-4be2076c1908"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Bottom">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_DICH}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="100" width="150" height="8" backcolor="#FFFFFF" uuid="647c5c86-9b79-4718-830f-c6599dd10158"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Codice Fiscale / Partita IVA]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="109" width="150" height="16" backcolor="#E0E0E0" uuid="abbee761-b513-4dc6-acc3-4be2076c1908"/>
				<box topPadding="0" leftPadding="5" bottomPadding="0" rightPadding="0">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CF_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="162" y="100" width="228" height="8" backcolor="#FFFFFF" uuid="30808b24-ea4f-465c-b759-b8785085c2f1"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Denominazione o Ragione Sociale]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="162" y="109" width="228" height="16" backcolor="#E0E0E0" uuid="f1cc5506-dfdb-4554-a411-4bde053f0947"/>
				<box topPadding="0" leftPadding="5" bottomPadding="0" rightPadding="0">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{RAGSOG_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="400" y="100" width="150" height="8" backcolor="#FFFFFF" uuid="4b0347c4-8249-448e-9086-682f79055ec2"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Natura Giuridica]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="400" y="109" width="150" height="16" backcolor="#E0E0E0" uuid="c64fe63d-3773-44b7-a4b8-86374b1d8a31"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NATGIU_GESTORE}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="137" width="65" height="16" backcolor="#E0E0E0" uuid="f55614e7-2af2-4936-8f8e-a2c7b1553fce"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEDIME_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="128" width="65" height="8" backcolor="#FFFFFF" uuid="16d52954-d552-44ce-842d-35ac28fca71e"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Sedime]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="77" y="137" width="210" height="16" backcolor="#E0E0E0" uuid="b853bcf2-ee9f-4b6c-a7d8-f24db979a167"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{IND_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="77" y="128" width="210" height="8" backcolor="#FFFFFF" uuid="78a837ed-a549-413a-bbd1-f3e441052dd3"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="137" width="106" height="16" backcolor="#E0E0E0" uuid="38955686-8fda-42d9-9f88-2361b245ff38"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COM_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="128" width="106" height="8" backcolor="#FFFFFF" uuid="be266fc0-25fd-40d3-8de8-fe428cc0b5a4"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Comune di domicilio Fiscale]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="405" y="137" width="50" height="16" backcolor="#E0E0E0" uuid="7a7492c3-d528-4e9d-97b8-d20f69007b50"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CAP_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="405" y="128" width="50" height="8" backcolor="#FFFFFF" uuid="b4f59c9a-e539-4a08-8118-7e5724dc88f2"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[CAP]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="462" y="137" width="88" height="16" backcolor="#E0E0E0" uuid="413df4cf-4ed3-4d00-af6b-03ac8d24ed2a"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PROV_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="462" y="128" width="88" height="8" backcolor="#FFFFFF" uuid="b905980d-c370-434d-9359-d96054f4f5bb"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Provincia/CM]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="166" width="126" height="16" backcolor="#E0E0E0" uuid="47add6c6-f4c5-4b27-8ea3-0d4cc50c0517"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TEL_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="157" width="126" height="8" backcolor="#FFFFFF" uuid="512138c6-58e3-4427-9255-e25bbf2abaae"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Numero di Telefono]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="141" y="166" width="200" height="16" backcolor="#E0E0E0" uuid="2c627bad-d0b3-4c96-9b07-22f3109fb151"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MAIL_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="141" y="157" width="200" height="8" backcolor="#FFFFFF" uuid="5ef6da4f-85c7-453d-a6e5-94b0052a61b7"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo di posta elettronica]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="346" y="166" width="204" height="16" backcolor="#E0E0E0" uuid="a0fb1ce1-dda5-41b9-b419-66d6fa021d94"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PEC_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="346" y="157" width="204" height="8" backcolor="#FFFFFF" uuid="88aeceae-ba1f-4331-a0f0-eba221d230ae"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Posta Elettronica Certificata]]></text>
			</staticText>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="5" y="187" width="545" height="15" uuid="da41f924-9027-4af5-873b-b417ccd3bfbd"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Bottom">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_L_R}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="205" width="150" height="8" backcolor="#FFFFFF" uuid="9935abc1-c7d0-4e94-9f0b-58460f6edc1a"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Codice Fiscale / Partita IVA]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="214" width="150" height="16" backcolor="#E0E0E0" uuid="da41f924-9027-4af5-873b-b417ccd3bfbd"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CF}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="162" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="25d5e2dd-5b5f-41bc-8f5c-63e86218aac2"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NOME}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="162" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="37954fc8-c01e-4c91-afa3-cd25975330ea"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Nome]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="4a87dca5-35c4-47ce-af8f-2265017048bb"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COGNOME}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="bb58e30b-a4a0-4dfe-85ec-d6ffd66b3311"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Cognome]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="425" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="d7950181-8f97-466b-8981-e489549696f4"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{QUALIFICA}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="425" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="02e348e4-cde9-4bc9-9fc2-46064e28c091"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Qualifica]]></text>
			</staticText>
			<rectangle>
				<reportElement x="0" y="240" width="555" height="2" backcolor="#000000" uuid="2778ddc8-3e81-45e4-b320-ac82a0936db1">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="6" y="420" width="544" height="15" uuid="d7950181-8f97-466b-8981-e489549696f4"/>
				<textElement verticalAlignment="Bottom">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_CONF}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="1" y="501" width="560" height="16" uuid="dd3c4230-91f8-47e8-aede-f536d45cfd5a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<graphicElement>
					<pen lineColor="#000000"/>
				</graphicElement>
			</rectangle>
			<staticText>
				<reportElement positionType="Float" x="16" y="438" width="100" height="12" backcolor="#FFFFFF" uuid="cb33ede1-cb40-4a9d-bfad-4582d9cc0eee">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[PRIMO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="16" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="7ef404e2-26df-44a4-8e64-a4c42abb4a17">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUANTITA'']]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="66" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="ab2130d7-f80b-4174-940d-f8f27530ded7">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="126" y="438" width="100" height="12" backcolor="#FFFFFF" uuid="83d65a98-28d6-4e81-b259-8f1db788320a">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[SECONDO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="126" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="336c1716-0ebf-4dfc-8bc5-3b531f35586c">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUANTITA'']]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="176" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="4e4c77b1-bbf4-423a-ab3a-87102b276713">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="236" y="438" width="100" height="12" backcolor="#FFFFFF" uuid="78b367bf-17ad-4ec7-bce2-0014d5e51a3a">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[TERZO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="236" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="675433c0-8927-4825-880d-92ca2720e105">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUANTITA'']]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="286" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="e5baa174-9988-428d-b285-c8bbbb7f5025">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="346" y="438" width="100" height="12" backcolor="#FFFFFF" uuid="23306f58-7d0a-4184-b774-6e956b20e94d">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUARTO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="346" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="cd70f275-c6f1-4984-9322-666edfdce610">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUANTITA'']]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="396" y="450" width="50" height="12" backcolor="#FFFFFF" uuid="43483503-8a92-453e-8a91-2f3d41f4cfa5">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="456" y="438" width="100" height="12" backcolor="#71B2DE" uuid="eadb5206-1e38-4ad8-ac26-45bad91133d2">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[TOTALE ANNUO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="456" y="450" width="50" height="12" backcolor="#71B2DE" uuid="0d4fbff9-5340-4ba7-99b6-66912c92644e">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUANTITA'']]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="506" y="450" width="50" height="12" backcolor="#71B2DE" uuid="ddaa1cc4-41e4-44d3-af88-33ba05f361f1">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<componentElement>
				<reportElement positionType="Float" stretchType="ElementGroupHeight" x="1" y="463" width="569" height="28" uuid="ab40e544-e566-4236-bac5-d13039f5c645">
					<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Vertical" ignoreWidth="true">
					<datasetRun subDataset="rifiutiConferitiDataset" uuid="d4891f28-817b-4bfe-92f0-9722bbf2511e">
						<dataSourceExpression><![CDATA[$P{rifiutiConferiti}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="28" width="569">
						<rectangle>
							<reportElement stretchType="ContainerHeight" x="0" y="0" width="560" height="28" backcolor="#FFFFFF" uuid="a39497d8-af5a-4a4b-882f-00acdea8c1e1">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
							</reportElement>
							<graphicElement>
								<pen lineColor="#000000"/>
							</graphicElement>
						</rectangle>
						<componentElement>
							<reportElement positionType="Float" x="15" y="14" width="110" height="14" uuid="ab40e544-e566-4236-bac5-d13039f5c645">
								<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<jr:list printOrder="Horizontal" ignoreWidth="true">
								<datasetRun subDataset="rifiutoConferitoDataset" uuid="d4891f28-817b-4bfe-92f0-9722bbf2511e">
									<dataSourceExpression><![CDATA[$F{conferimentiReport}]]></dataSourceExpression>
								</datasetRun>
								<jr:listContents height="14" width="110">
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
										<reportElement key="" x="0" y="0" width="50" height="12" backcolor="#E0E0E0" uuid="b12716b9-665b-4ef1-81b9-b5a0d65f9023">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
											<property name="com.jaspersoft.studio.unit.height" value="px"/>
											<property name="com.jaspersoft.studio.unit.y" value="px"/>
											<property name="com.jaspersoft.studio.unit.x" value="px"/>
										</reportElement>
										<box>
											<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
										</box>
										<textElement textAlignment="Center" verticalAlignment="Middle">
											<font size="8"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{quantita}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
										<reportElement key="" x="50" y="0" width="50" height="12" backcolor="#E0E0E0" uuid="f2ed5853-dacd-4ff0-b158-42c6e36d9a8e">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
											<property name="com.jaspersoft.studio.unit.height" value="px"/>
											<property name="com.jaspersoft.studio.unit.y" value="px"/>
											<property name="com.jaspersoft.studio.unit.x" value="px"/>
										</reportElement>
										<box>
											<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
											<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
										</box>
										<textElement textAlignment="Center" verticalAlignment="Middle">
											<font size="8"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{importo}]]></textFieldExpression>
									</textField>
								</jr:listContents>
							</jr:list>
						</componentElement>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Opaque" x="82" y="2" width="368" height="10" backcolor="#FFFFFF" uuid="8543b2cf-6873-4a19-ac0a-1e2ac820e803">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<pen lineWidth="0.5"/>
								<topPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rifiutoTariffa.descrizioneCompleta}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" mode="Opaque" x="50" y="2" width="30" height="10" backcolor="#FFFFFF" uuid="61a4964e-a18d-41ed-8f55-c345259aeb89">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box>
								<pen lineWidth="0.5"/>
								<topPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rifiutoTariffa.importo}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Opaque" x="15" y="2" width="33" height="10" backcolor="#FFFFFF" uuid="55949dbc-f3ad-4391-b4a2-4e66a91c854d">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box>
								<pen lineWidth="0.5" lineColor="#000000"/>
								<topPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{unitaMisura.descUnitaMisura}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Opaque" x="510" y="2" width="40" height="10" backcolor="#FFFFFF" uuid="628964a0-c723-43c1-adb8-900be6becccd">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rifiutoTariffa.stringRiduzione}]]></textFieldExpression>
						</textField>
						<staticText>
							<reportElement key="" mode="Transparent" x="470" y="2" width="80" height="10" backcolor="#FFFFFF" uuid="f6594a2b-fe0d-42c6-88bc-067a41d49fba">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement verticalAlignment="Middle">
								<font size="7" isBold="false"/>
							</textElement>
							<text><![CDATA[Riduzione]]></text>
						</staticText>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" x="454" y="14" width="50" height="12" backcolor="#E0E0E0" uuid="ef629aa8-8793-4845-8275-3c9b2c16cf58">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totale.quantita}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" x="504" y="14" width="50" height="12" backcolor="#E0E0E0" uuid="ba3f6bca-a175-4b9c-8cd7-ab1b1adedd62">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totale.importo}]]></textFieldExpression>
						</textField>
					</jr:listContents>
				</jr:list>
			</componentElement>
			<componentElement>
				<reportElement positionType="Float" x="16" y="503" width="110" height="14" uuid="7b1807ae-49fc-40d5-87a3-cf31a51afa80">
					<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Horizontal" ignoreWidth="true">
					<datasetRun subDataset="totalerifiutiConferitiDataset" uuid="183267fd-0642-4bbb-9eac-b45c722be1fc">
						<dataSourceExpression><![CDATA[$P{totalerifiutiConferiti}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="14" width="110">
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" x="0" y="0" width="50" height="12" backcolor="#E0E0E0" uuid="ed9df992-d84d-4649-a5b6-943173be0162">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totale.quantita}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" x="50" y="0" width="50" height="12" backcolor="#E0E0E0" uuid="17b4cc7c-b1ff-4d76-8427-a4e0cee3869f">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totale.importo}]]></textFieldExpression>
						</textField>
					</jr:listContents>
				</jr:list>
			</componentElement>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="505" y="503" width="50" height="12" backcolor="#E0E0E0" uuid="157a3b95-24ed-41b1-819b-2b18d902b160">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{totaleImporto}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="455" y="503" width="50" height="12" backcolor="#E0E0E0" uuid="ebbedd06-2b54-4c97-a51c-43680625fca5">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{totaleQuantita}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="243" width="544" height="15" uuid="8cb99f14-20c0-4e03-bc7f-90ddfef43026"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_IMP}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="260" width="246" height="8" backcolor="#FFFFFF" uuid="73b1e8dd-daff-4b10-8eb9-40ca5ed1ebee"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Denominazione impianto]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="269" width="246" height="16" backcolor="#E0E0E0" uuid="e859cf34-264a-4729-bef6-640ac5a56bf2"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NOME_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="261" y="269" width="129" height="16" backcolor="#E0E0E0" uuid="b75af9b2-80fc-4358-bde5-d6b960a03a05"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TIPO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="261" y="260" width="129" height="8" backcolor="#FFFFFF" uuid="f7439910-37c2-4dff-81c3-9d8b7e82105c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Tipo impianto]]></text>
			</staticText>
			<staticText>
				<reportElement x="402" y="260" width="148" height="8" backcolor="#FFFFFF" uuid="23aac261-5275-4d55-a270-a404daa680cc"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Stato impianto]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="402" y="269" width="148" height="16" backcolor="#E0E0E0" uuid="97a819a2-7bf4-40b1-8763-7f7e8cf5636f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{STATO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="298" width="65" height="16" backcolor="#E0E0E0" uuid="92d3d875-8780-4b01-8d29-b7202644a5ea"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEDIME_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="289" width="65" height="8" backcolor="#FFFFFF" uuid="a56fc885-c927-45fc-b4a9-6cb8fdd40661"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Sedime]]></text>
			</staticText>
			<staticText>
				<reportElement x="77" y="289" width="210" height="8" backcolor="#FFFFFF" uuid="d739abf6-5968-4e61-a172-38693e5e95d0"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="77" y="298" width="210" height="16" backcolor="#E0E0E0" uuid="9c61b92d-fb82-4984-990d-9480fbccedf4"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INDIRIZZO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="289" width="106" height="8" backcolor="#FFFFFF" uuid="cda824c0-42a5-4573-bb98-c963094c8915"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Comune di ubicazione]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="298" width="106" height="16" backcolor="#E0E0E0" uuid="8428699a-ac55-45d0-8eaf-d1934854af54"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COMUNE_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="405" y="298" width="50" height="16" backcolor="#E0E0E0" uuid="34eb9e04-63c9-403e-b6c0-06d86324bea1"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CAP_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="405" y="289" width="50" height="8" backcolor="#FFFFFF" uuid="4ab6c767-20bb-4afd-b5aa-1ce17aad7eac"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[CAP]]></text>
			</staticText>
			<staticText>
				<reportElement x="462" y="289" width="88" height="8" backcolor="#FFFFFF" uuid="64f75722-3ddd-43d6-a66c-57dc9ef6258f"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Provincia/CM]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="462" y="298" width="88" height="16" backcolor="#E0E0E0" uuid="9ade4724-48d7-43dd-ab42-29bb8285bedc"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PROV_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="319" width="544" height="15" uuid="8cb99f14-20c0-4e03-bc7f-90ddfef43026"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_P_A}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="6" y="367" width="544" height="15" uuid="8cb99f14-20c0-4e03-bc7f-90ddfef43026"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_L_DOC}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="5" y="393" width="65" height="16" backcolor="#E0E0E0" uuid="8cb99f14-20c0-4e03-bc7f-90ddfef43026"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEDIME_LUOGO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="5" y="384" width="65" height="8" backcolor="#FFFFFF" uuid="5841c123-0f37-4084-bbe5-223ef2426213"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Sedime]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="77" y="384" width="210" height="8" backcolor="#FFFFFF" uuid="6d1a23f3-b7a2-4f43-a1f6-0eb3d2c6b790"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="77" y="393" width="210" height="16" backcolor="#E0E0E0" uuid="6adfeccf-3a91-4e79-a8b0-f0fa9f1e78b7"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INDIRIZZO_LUOGO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="294" y="384" width="106" height="8" backcolor="#FFFFFF" uuid="5e72a495-5969-480e-a31d-a782f0ea8193"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Comune di ubicazione]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="294" y="393" width="106" height="16" backcolor="#E0E0E0" uuid="d69d243d-02a2-404f-ac00-07ccf1f52a89"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COMUNE_LUOGO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="405" y="393" width="50" height="16" backcolor="#E0E0E0" uuid="2b34c6f1-9b31-4bd5-a4b1-a0e0382bbc7b"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CAP_LUOGO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="405" y="384" width="50" height="8" backcolor="#FFFFFF" uuid="b218ca71-d847-49f7-9b14-cd74d98e1fc2"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[CAP]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="462" y="384" width="88" height="8" backcolor="#FFFFFF" uuid="0f265750-1962-44fa-b813-bec89a985090"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Provincia/CM]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="462" y="393" width="88" height="16" backcolor="#E0E0E0" uuid="68417100-b645-40e0-8832-3b6d00d380cf"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PROV_LUOGO}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="1" y="414" width="555" height="2" backcolor="#000000" uuid="e1bb48c0-c55b-4446-8663-3521c846ecd2">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<staticText>
				<reportElement x="5" y="336" width="110" height="8" backcolor="#FFFFFF" uuid="fad069ec-325f-4cdc-9b04-1bb712e60a29"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Numero Provvedimento]]></text>
			</staticText>
			<staticText>
				<reportElement x="120" y="336" width="110" height="8" backcolor="#FFFFFF" uuid="ddbdc110-455c-4f58-888f-789bc229c73f"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data Provvedimento]]></text>
			</staticText>
			<staticText>
				<reportElement x="233" y="336" width="104" height="8" backcolor="#FFFFFF" uuid="abd9cf60-95a0-4f1b-9608-f154ec7400a6"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Tipo Provvedimento]]></text>
			</staticText>
			<staticText>
				<reportElement x="341" y="336" width="104" height="8" backcolor="#FFFFFF" uuid="ce1fd9ca-e871-4ff2-b551-da2d9fbbc6a5"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data Decorrenza]]></text>
			</staticText>
			<staticText>
				<reportElement x="449" y="336" width="100" height="8" backcolor="#FFFFFF" uuid="5da78105-22a6-48cb-ab5f-ba3481c75184"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data Scadenza]]></text>
			</staticText>
			<componentElement>
				<reportElement stretchType="ElementGroupHeight" x="0" y="344" width="569" height="19" uuid="d0afa7f6-f525-40a8-8f00-333812b310cb">
					<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Vertical" ignoreWidth="true">
					<datasetRun subDataset="provvedimentiDataset" uuid="1789cf61-5021-442b-ae87-be244447f2e3">
						<dataSourceExpression><![CDATA[$P{provvedimenti}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="19" width="569">
						<rectangle>
							<reportElement stretchType="ContainerHeight" mode="Transparent" x="0" y="0" width="560" height="19" backcolor="#FFFFFF" uuid="94fdf0fa-82bb-4d23-8940-8c62669590bc">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
							</reportElement>
							<graphicElement>
								<pen lineColor="#FFFFFF"/>
							</graphicElement>
						</rectangle>
						<textField textAdjust="ScaleFont" isBlankWhenNull="true">
							<reportElement key="" x="5" y="0" width="110" height="16" backcolor="#E0E0E0" uuid="af5b58bd-a8cb-42e3-995f-257fa2bfa79d">
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement>
								<font size="10"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{numProvvedimento}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
							<reportElement key="" x="120" y="0" width="110" height="16" backcolor="#E0E0E0" uuid="2a76f28d-6783-45d4-a221-f8ac6903477c">
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement>
								<font size="10"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{dataProvvedimento}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" isBlankWhenNull="true">
							<reportElement key="" x="233" y="0" width="104" height="16" backcolor="#E0E0E0" uuid="b4d1aa8d-fb60-494b-b592-75d2d5f18352">
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement>
								<font size="10"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{tipoProvvedimento.descTipoProvvedimento}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
							<reportElement key="" x="341" y="0" width="104" height="16" backcolor="#E0E0E0" uuid="94cbb039-bf6a-462d-a828-3e15c06333fd">
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement>
								<font size="10"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{dataInizioAutorizzazione}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
							<reportElement key="" x="449" y="0" width="100" height="16" backcolor="#E0E0E0" uuid="065bf33a-f97e-4457-af02-0c8200a3a827">
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<box leftPadding="5">
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement>
								<font size="10"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{dataFineAutorizzazione}]]></textFieldExpression>
						</textField>
					</jr:listContents>
				</jr:list>
			</componentElement>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" x="5" y="520" width="544" height="15" uuid="8cb99f14-20c0-4e03-bc7f-90ddfef43026"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_VERS}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="4" y="538" width="90" height="12" backcolor="#FFFFFF" uuid="a79b060e-3e11-4aeb-b17d-67d4e4cf64be">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[PRIMO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="4" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="0a2c1c85-84b2-45da-ab59-8fdfee08cc21">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[DATA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="49" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="829f81de-eb5e-4131-82b7-529032bc6def">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="100" y="538" width="90" height="12" backcolor="#FFFFFF" uuid="8924e44f-d86f-41e5-a31c-71f493bc4090">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[SECONDO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="100" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="04add084-7347-44de-9856-66ffbafc79ef">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[DATA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="145" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="16053d5c-69fa-4c6f-aa1c-bbfb5eddd6f6">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="196" y="538" width="90" height="12" backcolor="#FFFFFF" uuid="ff8f9349-c666-40d3-ac48-00f619c3c9d3">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[TERZO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="196" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="626d97ce-6d37-489e-8160-a7f89a0b396b">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[DATA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="241" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="f61233bd-19ca-418c-97c4-e45f5e957c91">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="292" y="538" width="90" height="12" backcolor="#FFFFFF" uuid="9b64900b-6587-41a9-a908-a96ee1616fc4">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[QUARTO TRIMESTRE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="292" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="2a7abe41-f01c-438f-9ad6-b5be3aa0acd3">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[DATA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="337" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="ed57e3f4-c0dd-4c55-b05f-bc4661e93b1f">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="483" y="538" width="65" height="12" backcolor="#71B2DE" uuid="19420d99-4dba-44a0-b2e3-9df8771aceb4">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[TOTALE]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="483" y="550" width="65" height="12" backcolor="#71B2DE" uuid="5642bf9f-fafb-42ad-86af-f58c7b4d44aa">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="388" y="538" width="90" height="12" backcolor="#FFFFFF" uuid="4854717f-3f5c-4928-9033-d7e123fb964d">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[CONGUAGLIO]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="388" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="b115c353-c55d-4786-bbb5-8cafab51ed78">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[DATA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="433" y="550" width="45" height="12" backcolor="#FFFFFF" uuid="db41d014-ad04-4ee8-b434-159cf50461e1">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
				</textElement>
				<text><![CDATA[IMPORTO]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="483" y="562" width="65" height="12" backcolor="#E0E0E0" uuid="e94f8fb2-57ef-4aaa-ad7f-99870d564df9">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{versamentiTotale}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="142" y="577" width="195" height="12" backcolor="#FFFFFF" uuid="37a9bfe9-a805-4673-8a54-f59d9652712d">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Importo totale dovuto per l''anno di riferimento]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="343" y="577" width="71" height="12" backcolor="#E0E0E0" uuid="d69ec5c3-ee93-4529-9436-928371523843">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{totaleImporto}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="343" y="591" width="71" height="12" backcolor="#E0E0E0" uuid="fe11c48f-eb85-475d-bfa8-ebed99e535d6">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{versamentiTotale}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="142" y="591" width="195" height="12" backcolor="#FFFFFF" uuid="f3110847-2884-4dbd-a8f8-07ee88a732d5">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Importo totale versato]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="142" y="605" width="195" height="12" backcolor="#FFFFFF" uuid="b97140bd-24cf-433c-8cc1-e375d9977cce">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Credito risultante dalla precedente dichiarazione]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
				<reportElement key="" positionType="Float" x="343" y="605" width="71" height="12" backcolor="#E0E0E0" uuid="2446e427-17dd-4bed-a799-a906789a8a88">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{versamentiCreditoAP}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="142" y="619" width="195" height="15" backcolor="#FFFFFF" uuid="243cb2d3-5f36-4f64-bad5-a9c21b6a8d20">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Importo a debito (da versare a saldo)]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="false">
				<reportElement key="" positionType="Float" x="343" y="619" width="71" height="15" backcolor="#E0E0E0" uuid="186c5765-86c4-419a-88f4-a16bade0200d">
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{versamentiDebito}]]></textFieldExpression>
			</textField>
			<componentElement>
				<reportElement positionType="Float" x="4" y="562" width="96" height="14" uuid="30054741-0a8d-4ac4-869f-dfa1fad31c89">
					<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Horizontal" ignoreWidth="true">
					<datasetRun subDataset="versamentiDataset" uuid="4a5ce598-4bbe-436e-9b6f-493be8bc8a1e">
						<dataSourceExpression><![CDATA[$P{versamenti}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="14" width="96">
						<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
							<reportElement key="" x="0" y="0" width="45" height="12" backcolor="#E0E0E0" uuid="8542209c-5b33-41b5-9f78-9c676e56ce2a">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{dataVersamento}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" x="45" y="0" width="45" height="12" backcolor="#E0E0E0" uuid="25e0232e-bf26-4f8c-99d4-6b2a2a4fa2ce">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{importoVersato}]]></textFieldExpression>
						</textField>
					</jr:listContents>
				</jr:list>
			</componentElement>
			<rectangle>
				<reportElement positionType="Float" x="0" y="680" width="555" height="2" backcolor="#000000" uuid="7a00e85e-7b0d-4760-8f52-a34f93dccb95">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="5" y="686" width="544" height="15" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_VERS_MR}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="5" y="775" width="544" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_PIE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="5" y="786" width="26" height="10" backcolor="#FFFFFF" uuid="8be1c054-3684-4186-96e4-c62297effec2">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy">
				<reportElement key="" positionType="Float" x="31" y="786" width="117" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{dataDichiarazione}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="5" y="796" width="544" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_PIE_1}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="-1" y="806" width="555" height="2" backcolor="#000000" uuid="b1b28746-ca42-4cbc-9886-d6ec846a2cb8">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="4" y="808" width="544" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Top">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INFO_T}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="4" y="818" width="544" height="34" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INFO_D}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="0" y="773" width="555" height="2" backcolor="#000000" uuid="2d4f6fa3-1319-46e5-b7f7-6141c3a673b8">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="4" y="730" width="544" height="15" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_ANN}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" positionType="Float" stretchType="ElementGroupHeight" x="4" y="745" width="545" height="24" backcolor="#E0E0E0" uuid="5dcfbbdc-2a3f-416f-a74e-70e1cbac4013">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box topPadding="0" leftPadding="5" bottomPadding="0" rightPadding="0">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{annotazioni}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="101" y="700" width="35" height="12" backcolor="#FFFFFF" uuid="65eae63e-c83c-4a72-928f-ac8e4ab06c75">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Num.]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="136" y="700" width="115" height="12" backcolor="#FFFFFF" uuid="7bf73120-669e-40a7-a6d4-09c97fcc070d">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Codice Fiscale/Partita IVA]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="251" y="700" width="134" height="12" backcolor="#FFFFFF" uuid="8fd29b4e-7a3d-4212-af90-d1e2a3cfea9c">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Denominazione o Ragione sociale]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="385" y="700" width="77" height="12" backcolor="#FFFFFF" uuid="a0c35f16-52a3-4af2-a048-c03a4ff7030e">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Obiettivi raggiunti]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="101" y="712" width="35" height="12" backcolor="#FFFFFF" uuid="cdbc5418-2a77-4622-9a40-a63c57028277">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="136" y="712" width="115" height="12" backcolor="#FFFFFF" uuid="f80831f2-d8fd-421a-8f0a-d7cc9d3b4667">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="251" y="712" width="134" height="12" backcolor="#FFFFFF" uuid="f333d1e7-0bc0-430a-8364-5d2fee1366d2">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<staticText>
				<reportElement positionType="Float" x="385" y="712" width="77" height="12" backcolor="#FFFFFF" uuid="cbaff7fb-4b9e-4355-9e55-f0624846faf0">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<componentElement>
				<reportElement positionType="Float" stretchType="ElementGroupHeight" x="101" y="712" width="361" height="12" uuid="07d3356b-c8ec-49b6-a79a-fbd2d94e9e54">
					<property name="net.sf.jasperreports.export.headertoolbar.table.name" value="Soggetti"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.CONTENTS.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.x" value="px"/>
				</reportElement>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Vertical" ignoreWidth="true">
					<datasetRun subDataset="soggettimrDataset" uuid="f5c44942-3e91-4469-b9df-d02298255fa5">
						<dataSourceExpression><![CDATA[$P{soggettimr}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="12" width="361">
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Transparent" x="0" y="0" width="35" height="12" backcolor="#FFFFFF" uuid="9e417769-e7ad-45ee-bddb-bf0a1a02d7ad">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{idSoggettiMr}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Transparent" x="35" y="0" width="115" height="12" backcolor="#FFFFFF" uuid="f3d4c251-45cb-4016-9ffd-1de0f42f3870">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{codFiscPartiva}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Transparent" x="150" y="0" width="134" height="12" backcolor="#FFFFFF" uuid="b5d6b58e-9361-4fa5-9a8c-2436db8c4f04">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ragSociale}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" mode="Transparent" x="284" y="0" width="77" height="12" backcolor="#FFFFFF" uuid="bce3e2fd-8647-4fb9-a017-2e770d9d9aca">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<box>
								<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
								<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textElement textAlignment="Center" verticalAlignment="Middle">
								<font size="8" isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{obbRagg}]]></textFieldExpression>
						</textField>
					</jr:listContents>
				</jr:list>
			</componentElement>
			<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="false">
				<reportElement key="" positionType="Float" x="343" y="636" width="71" height="24" backcolor="#E0E0E0" uuid="c05ad6ca-14a5-4338-b170-da6094382cd0">
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="3"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{versamentiCredito}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="142" y="636" width="195" height="24" backcolor="#FFFFFF" uuid="0fa29d08-4134-4e9d-a6f7-2a191b9e4bb4">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph leftIndent="3"/>
				</textElement>
				<text><![CDATA[Importo a credito (da portare in diminuzione nella dichiarazione successiva)]]></text>
			</staticText>
		</band>
	</title>
</jasperReport>'
WHERE id_report = 1;